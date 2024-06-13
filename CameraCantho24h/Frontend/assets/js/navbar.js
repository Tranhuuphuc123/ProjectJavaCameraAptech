/* Phần thiết kế js cho mục navbar chủ yếu là xử lý cho đoạn navbar khi responsive */

//get element mục thẻ navbar - nut open và close menu btn
const navbar = document.querySelector(".header__container__navbar");
const openMenuBtn = document.querySelector(".open-menu-btn");
const closeMenuBtn = document.querySelector(".close-menu-btn");


[openMenuBtn, closeMenuBtn].forEach((btn) => {
    btn.addEventListener("click", () => {
        //thuộc tính classlist trong html dom dùng để lk thêm  xóa .. css cho element gốc
        navbar.classList.toggle("open")
        navbar.style.transition = "transform 0.5s ease"
    })
})

navbar.addEventListener("transitionend", function() {
    this.removeAttribute("style");
})


navbar.querySelectorAll(".dropdown").forEach((arrow) => {
    arrow.addEventListener("click", function() {
        this.closest(".dropdown").classList.toggle("active")
    })
})