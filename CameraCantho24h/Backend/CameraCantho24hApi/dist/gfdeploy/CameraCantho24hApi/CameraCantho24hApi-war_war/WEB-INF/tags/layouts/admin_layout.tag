<%@tag description="Admin layout" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" fragment="true" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="my-app-name" content="${pageContext.request.contextPath}">
    <!--link css-->
    <%@include file="../../../resources/admin/includes/css.jsp" %>
    
    <!--chèn thẻ <link> css riêng của từng trang 
     giải nghĩa: nghĩa là từng trang jsp có css riêng lẻ thì mọi lần
    gọi trang jsp đó thì nó sẽ import thẻ <link> tương ứng vào
    <=> vậy có hàng trăm trang jsp thì nó sẽ luân phiên thay đổi mà code 
    bên trang cố định nó không thay đổi gì
    -->
    <jsp:invoke fragment="styles" />
    
     <!--chèn logo wweb-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo/logo1.png" type="image/x-icon">
    <title><jsp:invoke fragment="title" /> | Admin CameraCantho24h</title>
</head>

<body>
    
    <!--import trang sidebar chung của giao diện admin-->
    <%@include file="../../../WEB-INF/tags/partials/admin/slidebar.jsp" %>
  
    <!--phần body là các trang tương ứng các mục trong sidebar khi click mục nào thì
    cái form trang tương ứng hiện ra-->
    <div class="p-4 sm:ml-64 pt-16">
        <jsp:doBody/>
    </div>
    <!--import js.jsp chứa tất cả js chung--->
    <%@include file="../../../resources/admin/includes/js.jsp" %>
    
    <!--chèn thẻ <link> js riêng của từng trang 
     giải nghĩa: nghĩa là từng trang jsp có js riêng lẻ thì mọi lần
    gọi trang jsp đó thì nó sẽ import thẻ <link> js tương ứng vào
    <=> vậy có hàng trăm trang jsp thì nó sẽ luân phiên thay đổi mà code 
    bên trang cố định nó không thay đổi gì
    -->
    <jsp:invoke fragment="scripts" />
</body>

</html>