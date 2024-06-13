<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--nạp giao diện chính của những layout cố định mà mình tách riêng ở mục
tags/layouts  (tránh viết đi viết lại như bên vscode làm giao diện thủ công)-->
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts/" %>

<!--gọi giao diện layout chính cố định ra -->
<layout:client_layout>
    <jsp:attribute name="title">
        Homepage
    </jsp:attribute>
        
    <jsp:attribute name="styles">
       <!--dân css tự viết riêng vào-->
       <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/client/css/pages/home.css"/>

       <!--css trực tiếp cho swipper-->
       <style>
           .swiper {
           width: 100%;
           padding-top: 50px;
           padding-bottom: 50px;
           background-color: #fff;
           }
       
           .swiper-slide {
           background-position: center;
           background-size: cover;
           width: 150px;
           height: 170px;
           }
       
           .swiper-slide img {
           display: block;
           width: 100%;
           }
       </style>
    </jsp:attribute>
       
    <jsp:attribute name="scripts">
        <!--dân js tự viết riêng vào-->
        <script src="${pageContext.request.contextPath}/resources/client/js/pages/home.js"></script>
    </jsp:attribute>
    
    <jsp:body>
        <!--phần body content mà mũng muons viết và chèn vào layouts client đã cố định-->
          <!--Thiết kế banner-->
          <div class="banner">
            <!--bnnner container-->
            <div class="banner__container">
                <div id="slide">
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner1.png);">
                        <div class="content">
                            <div class="name">Name</div>
                            <div class="des">thông tin đang bổ sung</div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner2.png);">
                        <div class="content">
                            <div class="name">NAME</div>
                            <div class="des">thông tin đang bổ sung</div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner1.png);">
                        <div class="content">
                            <div class="name">NAME</div>
                            <div class="des">thông tin đang bổ sung</div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner2.png);">
                        <div class="content">
                            <div class="name">NAME</div>
                            <div class="des">thông tin đang bổ sung</div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner1.png);">
                        <div class="content">
                            <div class="name">NAME</div>
                            <div class="des">thông tin đang bổ sung</div>
                            <button>See more</button>
                        </div>
                    </div>
                    <div class="item" style="background-image: url(${pageContext.request.contextPath}/resources/images/banner/banner2.png);">
                        <div class="content">
                            <div class="name">LUNDEV</div>
                            <div class="des">Tinh ru anh di chay pho, chua kip chay pho thi anhchay mat tieu</div>
                            <button>See more</button>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <button id="prev"><i class="fa-solid fa-angle-left"></i></button>
                    <button id="next"><i class="fa-solid fa-angle-right"></i></button>
                </div>
            </div>
        </div>
        
        
        <!--danh mục sản phẩm (sử dụng thư viện swipper js )-->
        <div class="swiper mySwiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <!-- <img src="./assets/image/Categories/camerahome.png" /> -->
                    <!--***-->
                    <div class="cardcate-all">
                        <div class="cardcate">
                            <div class="cardcate__main" style="--clr:#009688;">
                                <div class="img-box">
                                    <!-- Hình ảnh hoặc nội dung khác trong form box -->
                                    <img src="${pageContext.request.contextPath}/resources/images/categories/cameratrongnha.png" alt="">
                                </div>
                                <div class="content">
                                    <h2>Categories</h2>
                                    <p> thông tin cần thiết cho loại sản phẩm</p>
                                    <a href="">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--***-->
                <div class="swiper-slide">
                    <!-- <img src="./assets/image/Categories/camerahome.png" /> -->
                    <!-- Thẻ form box thay thế cho thẻ img -->
                    <div class="cardcate-all">
                        <div class="cardcate">
                            <div class="cardcate__main" style="--clr:#009688;">
                                <div class="img-box">
                                    <!-- Hình ảnh hoặc nội dung khác trong form box -->
                                    <img src="${pageContext.request.contextPath}/resources/images/categories/camera_outhome.png" alt="">
                                </div>
                                <div class="content">
                                    <h2>Categories</h2>
                                    <p>thông tin cần thiết cho loại sản phẩm</p>
                                    <a href="">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--***-->
                <div class="swiper-slide">
                    <!-- <img src="./assets/image/Categories/camerahome.png" /> -->
                    <!-- Thẻ form box thay thế cho thẻ img -->
                    <div class="cardcate-all">
                        <div class="cardcate">
                            <div class="cardcate__main" style="--clr:#009688;">
                                <div class="img-box">
                                    <!-- Hình ảnh hoặc nội dung khác trong form box -->
                                    <img src="${pageContext.request.contextPath}/resources/images/categories/camerakhongday.png" alt="">
                                </div>
                                <div class="content">
                                    <h2>Categories</h2>
                                    <p> thông tin cần thiết cho loại sản phẩm</p>
                                    <a href="">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--***-->
                <div class="swiper-slide">
                    <!-- <img src="./assets/image/Categories/camerahome.png" /> -->
                    <!-- Thẻ form box thay thế cho thẻ img -->
                    <div class="cardcate-all">
                        <div class="cardcate">
                            <div class="cardcate__main" style="--clr:#009688;">
                                <div class="img-box">
                                    <!-- Hình ảnh hoặc nội dung khác trong form box -->
                                    <img src="${pageContext.request.contextPath}/resources/images/categories/bulks.png" alt="">
                                </div>
                                <div class="content">
                                    <h2>Categories</h2>
                                    <p>thông tin cần thiết cho loại sản phẩm</p>
                                    <a href="">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--***-->

            </div>
            <div class="swiper-pagination"></div>
        </div>

        <!--Mục products cho trong trang homepage-->
        <div class="cardproduct-all">
            <div class="cardproduct-title">
               <p>PRODUCT NEWS</p>
            </div>   
            <div class="cardproduct-group">
              <div class="cardproduct">
                  <img src="${pageContext.request.contextPath}/resources/images/products/pd1.png" alt="">
                <div class="layer"></div>
                <div class="info">
                  <h1>product Name</h1>
                  <p>đây là đoạn thông tin mô tả sản phẩm đang trong quá trình hoàn thiện</p>
                  <button>Explore</button>
                  </div>
              </div>
        
              <div class="cardproduct">
                <img src="${pageContext.request.contextPath}/resources/images/products/pd2.png" alt="">
                <div class="layer"></div>
                <div class="info">
                  <h1>product Name</h1>
                  <p>đây là đoạn thông tin mô tả sản phẩm đang trong quá trình hoàn thiện</p>
                  <button>Explore</button>
                  </div>
              </div>
        
              <div class="cardproduct">
                  <img src="${pageContext.request.contextPath}/resources/images/products/pd3.png" alt="">
                <div class="layer"></div>
                <div class="info">
                  <h1>product Name</h1>
                  <p>đây là đoạn thông tin mô tả sản phẩm đang trong quá trình hoàn thiện</p>
                  <button>Explore</button>
                  </div>
              </div>
        
              
            </div>
        </div>

    </jsp:body>
</layout:client_layout>