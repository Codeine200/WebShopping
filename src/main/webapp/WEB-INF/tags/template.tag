<%@ tag description="head and title" pageEncoding="UTF-8"%>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>${title}</title>

    <script src="/includes/js/jquery-1.9.1.min.js"></script>


    <script src="/includes/js/bootstrap.min.js"></script>
    <link href="/includes/css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="/includes/css/custom.css" rel="stylesheet">

</head>

<body>
<div class="navbar navbar-default" role="navigation">

    <div class="navbar-inner">
        <a class="navbar-brand" href="/"><span>WebShopping</span></a>
    </div>
</div>

<div class="ch-container">
    <div class="row">
        <jsp:doBody/>
    </div>
</div>
</body>
</html>