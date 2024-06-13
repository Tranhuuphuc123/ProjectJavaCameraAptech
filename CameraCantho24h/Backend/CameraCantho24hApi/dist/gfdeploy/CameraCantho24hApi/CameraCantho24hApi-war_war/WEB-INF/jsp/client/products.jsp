<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--nạp giao diện chính của những layout cố định mà mình tách riêng ở mục
tags/layouts  (tránh viết đi viết lại như bên vscode làm giao diện thủ công)-->
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts/" %>

<layout:client_layout>
    <jsp:attribute name="title">
        Products
    </jsp:attribute>
        
    <jsp:attribute name="styles">
        <!--link dãn css cá nhân vào trang product-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/client/css/pages/product_productdetail.css"/>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <!--link dẫn js cá nhân trang product vào trong -->
        <script src="${pageContext.request.contextPath}/resources/client/js/pages/tabsUI.js"></script>
        <script src="${pageContext.request.contextPath}/resources/client/js/pages/product.js"></script>
    </jsp:attribute> 
        
    <jsp:body>
        <!--đoạn sẽ thay thế nội dung body ở thẻ doBody bên tags layout-->
        <!--nội dung product and product details-->
        <div class="tab-All">
           <!-- Tab items -->
            <div class="tabs">
                <div class="tab-item active" data-tab="react">Camera home</div>
                <div class="tab-item" data-tab="angular">Camera Outhome</div>
                <div class="tab-item" data-tab="ember">Link</div>
                <div class="tab-item" data-tab="vue">abc</div>
                <div class="line"></div>
            </div>
            
            <!-- Tab content -->
            <div class="tab-content">
                <div class="tab-pane active" id="react">
                    <div class="hopbox">
                        <!--load demo trang product và xử lý product details-->
                        <div class="product-content">
                            <div class="product-content__title">PRODUCT LIST</div>
                            <div class="listProduct">
                    
                            </div>
                        </div> 
                    </div>
                </div>

                <div class="tab-pane" id="angular">
                    <div class="hopbox">
                        <div class="product-content">
                            <div class="product-content__title">PRODUCT CAMERA</div>
                            <div class="listProduct">
                    
                            </div>
                        </div> 
                    </div>
                </div>
                <div class="tab-pane" id="ember">
                    <div class="hopbox"></div>
                </div>   
                <div class="tab-pane" id="vue">
                    <div class="hopbox"></div>
                </div>
            </div>   
            
        </div><!--end-->
    </jsp:body>
           
</layout:client_layout>