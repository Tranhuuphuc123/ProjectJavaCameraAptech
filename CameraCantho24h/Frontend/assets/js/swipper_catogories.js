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
