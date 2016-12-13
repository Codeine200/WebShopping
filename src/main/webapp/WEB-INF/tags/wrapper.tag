<%@ tag description="head and title" pageEncoding="UTF-8"%>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>
<%@ attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>${title}</title>

    <script src="${contextPath}/includes/js/jquery-1.9.1.min.js"></script>

    <script src="${contextPath}/includes/js/bootstrap.min.js"></script>
    <link href="${contextPath}/includes/css/bootstrap-cerulean.min.css" rel="stylesheet">
    <link href="${contextPath}/includes/css/custom.css" rel="stylesheet">

</head>

<body>
<div class="navbar navbar-default" role="navigation">

    <div class="navbar-inner">

        <a class="navbar-brand" href="/admin/"><span>WebShopping</span></a>

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


        <ul class="collapse navbar-collapse nav navbar-nav top-menu">
            <li><a href="/"><i class="glyphicon glyphicon-globe"></i> <local:message code="common.visit" /></a></li>
        </ul>

    </div>
</div>
<div class="ch-container">
    <div class="row">
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header"><local:message code="common.main" /></li>
                        <li><a class="ajax-link" href="${contextPath}/admin/products"><i class="glyphicon glyphicon-list-alt"></i><span> <local:message code="menu.products" /></span></a></li>
                        <li><a class="ajax-link" href="${contextPath}/admin/categories"><i class="glyphicon glyphicon-list"></i><span> <local:message code="menu.categories" /></span></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
                <jsp:doBody/>
            </div>
        </div>
    </div>
</div>
<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>