/**trang product detail sản phẩn chi tiết***/
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--nạp giao diện chính của những layout cố định mà mình tách riêng ở mục
tags/layouts  (tránh viết đi viết lại như bên vscode làm giao diện thủ công)-->
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts/" %>

<layout:client_layout>
    <jsp:attribute name="title">
        ProductDetail
    </jsp:attribute>
        
     <jsp:attribute name="styles">
        <!--link dãn css cá nhân vào trang product-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/client/css/pages/product_productdetail.css"/>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <!--link dẫn js cá nhân trang product vào trong -->
        <script src="${pageContext.request.contextPath}/resources/client/js/pages/productdetail.js"></script>
    </jsp:attribute> 
        
        
        
    <jsp:body>
        <!--đoạn sẽ thay thế nội dung body ở thẻ doBody bên tags layout-->
         <!--mục product details-->
        <div class="product-content">
            <div class="product-content__title">PRODUCT DETAIL</div>
            <div class="product-content__detail">
                <div class="product-content__detail--img">
                    <img src="">
                </div>
                <div class="product-content__detail--content">
                    <h1 class="product-name"></h1>
                    <div class="product-price"></div>
                    <div class="product-buttons">
                        <button>Check Out</button>
                        <button>Add To Cart 
                            <span>
                                <svg class="" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 18 20">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 15a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm0 0h8m-8 0-1-4m9 4a2 2 0 1 0 0 4 2 2 0 0 0 0-4Zm-9-4h10l2-7H3m2 7L3 4m0 0-.792-3H1"/>
                                </svg>
                            </span>
                        </button>
                    </div>
                    <div class="product-description"></div>
                </div>
            </div>

            <div class="product-content__title">Similar product</div>
            <div class="listProduct"></div>
            
        </div><!--end-->
   </jsp:body>
           
</layout:client_layout>