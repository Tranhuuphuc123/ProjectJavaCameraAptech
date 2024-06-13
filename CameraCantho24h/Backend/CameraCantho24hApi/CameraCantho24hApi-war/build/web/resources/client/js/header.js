/*xử lý của mục header*/
/* Phần thiết kế js cho mục navbar chủ yếu là xử lý cho đoạn navbar khi responsive */





/******phần xử lý thanh nab******/
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




/*****phần xử lý modal signup/signin******/
/*xử lý các chức năng form */




/*********************************************************************************** */
/*******************TRONG PHẦN NÀY TA XỬ LÝ FORM LOG IN VÀ SIGN UP *******************/

const signUpButton = $('.button-signup');
const signInButton = $('.button-signin');
const modal = $('.modal');
const modalSub = $('.modal__body-sub');
const signUpForm = $('.auth-form__register');
const signInForm = $('.auth-form__login');
const backButton = $$('.btn--back')

// console.log(signInButton)
// console.log(signUpButton)
// console.log(modal)
// console.log(signUpForm)
// console.log(signInForm)

//Xử lý khi nhấn vào nút "Đăng ký"
signUpButton.onclick = () => {
    modal.style.display = 'flex';
    signUpForm.classList.remove('auth-form--display');
    signInForm.classList.add('auth-form--display');
};

// Xử lý khi nhấn vào nút "Đăng nhập"
signInButton.onclick = () => {
    modal.style.display = 'flex';
    signInForm.classList.remove('auth-form--display');
    signUpForm.classList.add('auth-form--display');
};

// Lặp qua từng nút "Trở lại" và gán sự kiện onclick
backButton.forEach(button => {
    button.onclick = () => {
        modal.style.display = 'none';
    };
});

 /* xử lý sự kiện khi nhấn vào vùng trống thì lặp tức nguyên modal hộp thoại from login/signup nó sẽ ẩn 
mà hông cần nhấn dấu x hay trở lại*/
modal.onclick = () => {
    modal.style.display = 'none';
}

/*tạo xử lý ngăn sự kiện nổi bọt -> nghĩa là khi modal đc onclick dẫn đến hệ quả
-> là khi nhấp bất kỳ cái tp nào trong hộp thoại modal tickets nó cũng bắt sự kiện
modal.onclik làm cho hộp thoại bị tắt 
-> giả dụ mún nhập thông tin thì khồng làm đc nữa(đây là sự kiện nổi bọt trong Js)
<==> để khăc phục ta */
modalSub.onclick = (e) => {
    // thuộc tính stopPropagation giúp ngăn sự kiện nổi bọt gây ảnh hưởng hiểu ứng lên toàn hộp thoại modal tickets
    e.stopPropagation()
}