<%@tag description="Client layout" pageEncoding="UTF-8"%>
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
    <!--link css base-->
    <%@include file="../../../resources/client/includes/css.jsp" %>
    
    <!--chèn thẻ <link> css riêng của từng trang 
     giải nghĩa: nghĩa là từng trang jsp có css riêng lẻ thì mọi lần
    gọi trang jsp đó thì nó sẽ import thẻ <link> tương ứng vào
    <=> vậy có hàng trăm trang jsp thì nó sẽ luân phiên thay đổi mà code 
    bên trang cố định nó không thay đổi gì
    -->
    <jsp:invoke fragment="styles" />
    
     <!--chèn logo wweb-->
     <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo/logo1.png" type="image/x-icon">
    <title><jsp:invoke fragment="title" /> | CameraCantho24h</title>
</head>

<body>
    <!--mục header cùng với thẻ navbar-->
    <%@include file="../../../WEB-INF/tags/partials/client/header.jsp" %>
 
    <!--phần container body chính thân của web-->
    <div class="container">
        <!--chèn các nội dung thay đổi luân phiên trong trang client có định-->
        <jsp:doBody />
    </div><!--end container của body main-->

    <!--footer 
    => sử dụng cách import bên jstl java vào -->
    <%@include file="../../../WEB-INF/tags/partials/client/footer.jsp" %>
   
  
    <!--import js.jsp chứa tất cả js chung--->
    <%@include file="../../../resources/client/includes/js.jsp" %>
    
    <!--chèn thẻ <link> js riêng của từng trang 
     giải nghĩa: nghĩa là từng trang jsp có js riêng lẻ thì mọi lần
    gọi trang jsp đó thì nó sẽ import thẻ <link> js tương ứng vào
    <=> vậy có hàng trăm trang jsp thì nó sẽ luân phiên thay đổi mà code 
    bên trang cố định nó không thay đổi gì
    -->
    <jsp:invoke fragment="scripts" />
    
   
</body>

</html>