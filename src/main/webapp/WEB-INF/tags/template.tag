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


        <!-- user dropdown starts and languages -->
        <div class="btn-group pull-right">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${pageContext.request.userPrincipal.name}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#"><local:message code="common.profile" />
                    </a></li>
                    <li class="divider"></li>
                    <li><a onclick="document.forms['logoutForm'].submit()"> <local:message code="users.logout" /></a></li>
                </ul>
            </c:if>
            &nbsp;<a href="?language=en"><img src="${contextPath}/includes/img/en.png" /></a> <a href="?language=ru"><img src="${contextPath}/includes/img/ru.png" /></a>
        </div>
        <!-- user dropdown ends -->

    </div>
</div>

<div class="ch-container">
    <div class="row">
        <jsp:doBody/>
    </div>
</div>
<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>