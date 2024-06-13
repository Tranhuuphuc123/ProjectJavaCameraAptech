document.addEventListener("DOMContentLoaded", function(){
    /*     // AJAX: Async JavaScript and XML
     => tiềm hiểu về AJAX CHO KÝ HƠN VÌ TRONG BÀI NÀY XỬ LÝ KỸ THUẬT VỚI AJAX
    */
    
    //01 - xử lý render value (đổ value từ csdl vào table suppliers trên giao diện admin )
    function fetchTableData() {
        const fetchPath = window.APP_NAME + '/api/suppliers/all';
        fetch(fetchPath)
            .then(res => res.json())
            .then(res => {
                if (res.code == 200) {
                    document.getElementById('table-body').innerHTML = 
                        res.data.map(item => {
                            return `
                                <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        <input type="checkbox" name="delete-checkbox" value="${item.supplierID}" class="cursor-pointer w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                                    </th>
                                    <td class="px-6 py-4">
                                        ${item.supplierName}
                                    </td>
                                    <td class="px-6 py-4">
                                        ${item.phone}
                                    </td>
                                    <td class="px-6 py-4">
                                        ${item.email}
                                    </td>
                                    <td class="px-6 py-4">
                                        ${item.description}
                                    </td>
                                    <td class="px-6 py-4 text-right">
                                        <a data-edit-id="${item.supplierID}" data-modal-target="update-modal" data-modal-toggle="update-modal" class="cursor-pointer font-medium text-yellow-400 dark:text-yellow-400 hover:underline">
                                            <i class="fa-solid fa-pen"></i>
                                        </a>
                                    </td>
                                </tr>
                            `
                    }).join(''); // ['a', 'b', 'c'] sau map -> ['a1', 'b1', 'c1'] -> sau join('') -> 'a1b1c1'
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
                            fetch(`${window.APP_NAME}/api/suppliers/delete?ids=${checkedRowsToDelete.join(',')}`, {
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
        

                    // 04 - 02 Handle click edit: ()
                    const editBtns = document.querySelectorAll('a[data-edit-id]');
                    Array.from(editBtns).forEach(btn => {
                        btn.onclick = () => {
                            const supplier = res.data.find(item => item.supplierID == btn.dataset.editId);
        
                            document.getElementById('supplierIDEdit').value = supplier.supplierID;
                            document.getElementById('supplierNameEdit').value = supplier.supplierName;
                            document.getElementById('phoneEdit').value = supplier.phone;
                            document.getElementById('emailEdit').value = supplier.email;
                            document.getElementById('descriptionEdit').value = supplier.description;
                        }
                    })
                }
            })
    }
    fetchTableData();






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
            Validator.isRequired('#supplierName', 'Name is required'),
            Validator.isRequired('#email', 'Email is required'),
            Validator.isRequired('#phone', 'Phone is required'),
            Validator.isRequired('#description', 'Description is required'),
        ],
        onSubmit: function(data, { resetForm }) {
            const storePath = window.APP_NAME + '/api/suppliers/store';
            fetch(storePath, {
                method: 'POST',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
            .then(res => res.json())
            .then(res => {
                if (res.code == 200) {
                    document.getElementById('close-create-modal-btn').click();
                    hideWarningAlert();
                    showSuccessAlert('Successfully create new supplier');
                    fetchTableData();
                    // resetForm({
                    //     roleID: '',
                    //     roleName: '',
                    // })
                } else if (res.code == 400 && res.invalid) {
                    showWarningAlert('Invalid some fields', res.errors);
                    document.getElementById('close-create-modal-btn').click();
                }
            })
            .catch(_err => {})
        }
    });




    //04 - 01 - Update
    Validator({
        form: '#update-form',
        formGroup: '.form-gr',
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired('#supplierIDEdit', 'ID is required'),
            Validator.isRequired('#supplierNameEdit', 'Name is required'),
            Validator.isRequired('#emailEdit', 'Email is required'),
            Validator.isRequired('#phoneEdit', 'Phone is required'),
            Validator.isRequired('#descriptionEdit', 'Description is required'),
        ],
        onSubmit: function(data, { resetForm }) {
            const url = window.APP_NAME + '/api/suppliers/update';
            fetch(url, {
                method: 'PUT',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
            .then(res => res.json())
            .then(res => {
                if (res.code == 200) {
                    document.getElementById('close-update-modal-btn').click();
                    hideWarningAlert();
                    showSuccessAlert('Successfully update supplier');
                    fetchTableData();
                    // resetForm({
                    //     roleID: '',
                    //     roleName: '',
                    // })
                } else if (res.code == 400 && res.invalid) {
                    showWarningAlert('Invalid some fields', res.errors);
                    document.getElementById('close-update-modal-btn').click();
                }
            })
            .catch(_err => {})
        }
    });
    
    
});