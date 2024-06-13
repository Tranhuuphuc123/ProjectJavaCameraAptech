/****************trang js rieeng của trang homepage******************/

/***banner mục quảng cáo****/

// Tạo biến để lưu trữ thời gian chờ giữa các chuyển đổi ảnh (đơn vị là mili giây)
let intervalTime = 3000; // Ví dụ: 3 giây

// Tạo hàm tự động chuyển đổi ảnh
function autoSlide() {
    // Chuyển đến ảnh tiếp theo
    let lists = document.querySelectorAll('.item');
    document.getElementById('slide').appendChild(lists[0]);
}

// Sử dụng setInterval để gọi hàm autoSlide định kỳ
let slideInterval = setInterval(autoSlide, intervalTime);

// Tạm dừng chuyển đổi tự động khi rê chuột vào banner
document.querySelector('.banner').addEventListener('mouseenter', function() {
    clearInterval(slideInterval);
});

// Tiếp tục chuyển đổi tự động khi rời chuột ra khỏi banner
document.querySelector('.banner').addEventListener('mouseleave', function() {
    slideInterval = setInterval(autoSlide, intervalTime);
});

// Xử lý sự kiện khi bấm nút Next
document.getElementById('next').onclick = function() {
    autoSlide(); // Gọi hàm autoSlide để chuyển đến ảnh tiếp theo
};

// Xử lý sự kiện khi bấm nút Prev
document.getElementById('prev').onclick = function() {
    // Chuyển đến ảnh trước đó
    let lists = document.querySelectorAll('.item');
    document.getElementById('slide').prepend(lists[lists.length - 1]);
};




/*******phần swipper quảng cáo**** */
document.addEventListener("DOMContentLoaded", function () {
    var swiperContainer = document.querySelector(".mySwiper");
    var swiper = new Swiper(swiperContainer, {
      effect: "coverflow",
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: "auto",
      coverflowEffect: {
        rotate: 0,  //goc độ xoay của mỗi ảnh
        stretch: -10, // mức độ căng ra, dãn ra của các line ảnh (k/c)
        depth: 10, //Đây là độ sâu của các slide. Điều này ảnh hưởng đến cảm giác của hiệu ứng 3D.
        modifier: 10, //tốc độ và cảm giác trượt của hiệu ứng
        slideShadows: true,
      },
      pagination: {
        el: swiperContainer.querySelector(".swiper-pagination"),
      },
    });
  });
  