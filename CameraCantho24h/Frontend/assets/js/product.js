/*************************************************************** */
/********Đoạn này js cho product******* */
let products = null;

// Get data from JSON file
fetch('../../productInfo.json')
    .then(response => response.json())
    .then(data => {
        products = data;
        addDataToHTML('react');
    })
    .catch(error => {
        console.error('Error fetching the JSON file:', error);
    });

function addDataToHTML(category) {
    // Remove existing data from HTML
    let listProductHTML = document.querySelector(`#${category} .listProduct`);
    listProductHTML.innerHTML = '';

    // Add new data
    if (products !== null) { // if has data
        products.forEach(product => {
            let newProduct = document.createElement('a');
            newProduct.href = '../../ProductDetail.html?id=' + product.id;
            newProduct.classList.add('productList-item');
            newProduct.innerHTML =
               `<img src="${product.image}" alt="">
                <h2>${product.name}</h2>
                <div class="price">$${product.price}</div>`;
            listProductHTML.appendChild(newProduct);
        });
    }
}

// Tab switching logic
document.querySelectorAll('.tab-item').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.tab-item').forEach(item => item.classList.remove('active'));
        document.querySelectorAll('.tab-pane').forEach(pane => pane.classList.remove('active'));
        
        tab.classList.add('active');
        document.getElementById(tab.getAttribute('data-tab')).classList.add('active');
        
        addDataToHTML(tab.getAttribute('data-tab'));
    });
});
