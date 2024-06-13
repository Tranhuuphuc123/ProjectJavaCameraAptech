

//01- render value vao giao dien
function fetchTableData() {
    const fetchPath = window.APP_NAME + '/api/users/all';
    fetch(fetchPath)
        .then(res => res.json())
        .then(res => {
            if (res.code == 200) {
                document.getElementById('table-body').innerHTML = 
                    res.data.map(item => {
                        return `
                            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <input type="checkbox" name="delete-checkbox" value="${item.userID}" class="cursor-pointer w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                                </th>
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <img class="object-cover w-20 h-20 rounded" src="${item.avatar}" alt="Large avatar">
                                </th>
                                <td class="px-6 py-4">
                                    ${item.fullname}
                                </td>
                                <td class="px-6 py-4">
                                    ${item.address}
                                </td>
                                <td class="px-6 py-4">
                                    ${item.email}
                                </td>
                                <td class="px-6 py-4">
                                    ${item.phone}
                                </td>
                                <td class="px-6 py-4">
                                    ${item.role}
                                </td>
                                <td class="px-6 py-4 text-right">
                                    <a data-edit-id="${item.userID}" data-modal-target="update-modal" data-modal-toggle="update-modal" class="cursor-pointer font-medium text-yellow-400 dark:text-yellow-400 hover:underline">
                                        <i class="fa-solid fa-pen"></i>
                                    </a>
                                </td>
                            </tr>
                        `
                }).join('');
                // Init flowbite
                initFlowbite();


            
                  //05 -  Handle checkbox to delete (XỬ LÝ khi nhấn check box value cần xóa nút delete hiện ra và xử lý xóa value đo)
                let checkedRowsToDelete = [];

                function renderDeleteBtn() {
                    const dltBtn = document.getElementById('dlt-btn');
                    if (checkedRowsToDelete.length > 0) {
                        dltBtn.classList.remove('hidden')
                    } else {
                        dltBtn.classList.add('hidden')
                    }

                    const yesDltBtn = document.getElementById('yes-dlt-btn')
                    yesDltBtn.onclick = () => {
                        fetch(`${window.APP_NAME}/api/users/delete?ids=${checkedRowsToDelete.join(',')}`, {
                            method: 'DELETE',
                            credentials: 'same-origin',
                        })
                        .then(res => res.json())
                        .then(res => {
                            if (res.code == 200) {
                                hideWarningAlert();
                                showSuccessAlert('Successfully delete');
                                fetchTableData();
                                checkedRowsToDelete = [];
                                renderDeleteBtn();
                            }
                        })
                        .catch(_err => {})
                    }
                }

                const deleteCheckboxs = document.querySelectorAll('input[name="delete-checkbox"]');
                deleteCheckboxs.forEach(checkbox => {
                    checkbox.oninput = e => {
                        if (e.target.checked) {
                            checkedRowsToDelete.push(checkbox.value);
                        } else {
                            checkedRowsToDelete = checkedRowsToDelete.filter(item => item != checkbox.value);
                        }
                        renderDeleteBtn();
                    }
                })

                //04-02 handle edit 
                const editBtns = document.querySelectorAll('a[data-edit-id]');
                Array.from(editBtns).forEach(btn => {
                    btn.onclick = () => {
                        const user = res.data.find(item => item.userID == btn.dataset.editId);

                        // Load image to img tag
                        document.getElementById('previewImgEdit').src = user.avatar;
                        document.getElementById('previewImgEdit').classList.remove('hidden')

                        document.getElementById('userIDEdit').value = user.userID;
                        document.getElementById('fullNameEdit').value = user.fullname;
                        document.getElementById('passwordEdit').value = '';
                        document.getElementById('addressEdit').value = user.address;
                        document.getElementById('phoneEdit').value = user.phone;
                        document.getElementById('emailEdit').value = user.email;
                        document.getElementById('roleEdit').value = user.role;
                        document.getElementById('genderEdit').value = user.gender;

                    }
                })

            
            } //end if -else
            
        })
}//end 01 function fetchTableData

fetchTableData()

//02 -  Alerts (tạo thông báo thành công khi update - create - delete)
let successAlert = "";

function showSuccessAlert(content) {
    const sAElm = document.getElementById('success-alert');
    const sAContentElm = document.getElementById('success-alert-content');

    sAContentElm.textContent = content;

    sAElm.classList.remove('opacity-0');
    sAElm.classList.remove('hidden');
    sAElm.classList.add('flex');
}

let warningAlert = [];

function showWarningAlert(titleContent = '', alerts = []) {
    const wrapper = document.getElementById('invalid-alert');
    const title = document.getElementById('invalid-alert-title');
    const list = document.getElementById('invalid-alert-list');

    title.textContent = titleContent;
    list.innerHTML = alerts.map(alert => `
        <li>${alert}</li>
    `).join('');

    wrapper.classList.remove('hidden');
    wrapper.classList.add('flex');
}

function hideWarningAlert() {
    const wrapper = document.getElementById('invalid-alert');

    wrapper.classList.remove('flex');
    wrapper.classList.add('hidden');
}









//03 -  Validate create khởi tạo value (insert value for suppliers table)
Validator({
    form: '#create-form',
    formGroup: '.form-gr',
    errorSelector: '.form-message',
    rules: [
        Validator.isRequired('#image', 'Image is required'),
        Validator.isRequired('#fullName', 'Fullname is required'),
        Validator.isRequired('#password', 'Password is required'),
        Validator.isRequired('#address', 'Address is required'),
        Validator.isRequired('#phone', 'Phone is required'),
        Validator.isRequired('#email', 'Email is required'),
        Validator.isRequired('#role', 'Role is required'),
        Validator.isRequired('#gender', 'Gender is required'),
    ],
    onSubmit: function(data, { resetForm }) {
        const path = window.APP_NAME + '/api/users/store';
        const { image, ...otherData } = data;
        const imageFile = image.length > 0 ? image[0] : null;
        
        const formData = new FormData();

        formData.append("image", imageFile);
        formData.append("data", new Blob(
            [
                JSON.stringify(otherData)
            ],
            {
                type: "application/json"
            }
        ))

        fetch(path, {
            method: 'POST',
            credentials: 'same-origin',
            body: formData,
        })
        .then(res => res.json())
        .then(res => {
            if (res.code == 200) {
                document.getElementById('close-create-modal-btn').click();
                hideWarningAlert();
                showSuccessAlert(res.message);
                fetchTableData();
                resetPreviewImage('#previewImg')
                resetForm()
            } else if (res.code == 400 && res.invalid) {
                showWarningAlert('Invalid some fields', res.errors);
                document.getElementById('close-create-modal-btn').click();
            }
        })
        .catch(_err => {})
    }
});



//04-01 update user
Validator({
    form: '#update-form',
    formGroup: '.form-gr',
    errorSelector: '.form-message',
    rules: [
        Validator.isRequired('#userIDEdit', 'User ID is required'),
        Validator.isRequired('#fullNameEdit', 'Fullname is required'),
        Validator.isRequired('#addressEdit', 'Address is required'),
        Validator.isRequired('#phoneEdit', 'Phone is required'),
        Validator.isRequired('#emailEdit', 'Email is required'),
        Validator.isRequired('#roleEdit', 'Role is required'),
        Validator.isRequired('#genderEdit', 'Gender is required'),
    ],
    onSubmit: function(data, { resetForm }) {
        const path = window.APP_NAME + '/api/users/update';
        const { image, ...otherData } = data;
        const imageFile = image.length > 0 ? image[0] : null;
        
        const formData = new FormData();

        formData.append("image", imageFile);
        formData.append("data", new Blob(
            [
                JSON.stringify(otherData)
            ],
            {
                type: "application/json"
            }
        ))

        fetch(path, {
            method: 'POST',
            credentials: 'same-origin',
            body: formData,
        })
        .then(res => res.json())
        .then(res => {
            if (res.code == 200) {
                document.getElementById('close-update-modal-btn').click();
                hideWarningAlert();
                showSuccessAlert(res.message);
                fetchTableData();
                resetPreviewImage('#previewImgEdit')
                resetForm({
                    password: ''
                })
            } else if (res.code == 400 && res.invalid) {
                showWarningAlert('Invalid some fields', res.errors);
                document.getElementById('close-update-modal-btn').click();
            }
        })
        .catch(_err => {})
    }
});

// Handle preview image before upload
function previewImageBeforeUploadListener({ inputSelector, previewerSelector }) {
    const input = document.querySelector(inputSelector);
    const previewImg = document.querySelector(previewerSelector);

    input.onchange = () => {
        const [file] = input.files;
        if (file) {
            previewImg.src = URL.createObjectURL(file);
            previewImg.classList.remove('hidden')
        }
    }
}

// for create form
previewImageBeforeUploadListener({
    inputSelector: '#image',
    previewerSelector: '#previewImg'
});
// for update form
previewImageBeforeUploadListener({
    inputSelector: '#imageEdit',
    previewerSelector: '#previewImgEdit'
});
function resetPreviewImage(previewerSelector) {
    const previewImg = document.querySelector(previewerSelector);
    if (previewImg) {
        previewImg.src = ''
        previewImg.classList.add('hidden')
    }
}





