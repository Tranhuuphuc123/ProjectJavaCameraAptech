<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--nạp giao diện chính của những layout cố định mà mình tách riêng ở mục
tags/layouts  (tránh viết đi viết lại như bên vscode làm giao diện thủ công)-->
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts/" %>

<!--gọi giao diện layout chính cố định ra -->
<layout:admin_layout>
    <jsp:attribute name="title">
        Dashboard
    </jsp:attribute>
        
    <jsp:attribute name="styles">
    </jsp:attribute>
    <jsp:attribute name="scripts">
    </jsp:attribute>
    
    <jsp:body>
        Welcome to CameraCantho24h
    </jsp:body>
        
        
             
  
</layout:admin_layout>