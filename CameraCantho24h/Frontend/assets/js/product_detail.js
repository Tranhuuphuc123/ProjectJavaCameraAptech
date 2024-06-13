
/*************************************************************** */
/****get datas from file json***/
let products = null;
fetch('../../productInfo.json')
    .then(response => response.json())
    .then(data => {
        products = data;
        showDetail();
})

    function showDetail(){
        // remove datas default from HTML
        let detail = document.querySelector('.product-content__detail');
        let listProduct = document.querySelector('.listProduct');
        let productId =  new URLSearchParams(window.location.search).get('id');
        let thisProduct = products.filter(value => value.id == productId)[0];
        //if there is no product with id = productId => return to home page
        if(!thisProduct){
        window.location.href = "/";
    }

    detail.querySelector('.product-content__detail--img img').src = thisProduct.image;
    detail.querySelector('.product-name').innerText = thisProduct.name;
    detail.querySelector('.product-price').innerText = '$' + thisProduct.price;
    detail.querySelector('.product-description').innerText = '$' + thisProduct.description;


    (products.filter(value => value.id != productId)).forEach(product => {
        let newProduct = document.createElement('a');
        newProduct.href = '../../ProductDetail.html?id=' + product.id;
        newProduct.classList.add('productList-item');
        newProduct.innerHTML = 
        `<img src="${product.image}" alt="">
        <h2>${product.name}</h2>
        <div class="price">$${product.price}</div>`;
        listProduct.appendChild(newProduct);
    });
}