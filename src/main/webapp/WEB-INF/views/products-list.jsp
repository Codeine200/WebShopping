<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="List of Products">

<div class="additional_menu"><a href="${contextPath}/admin/products/new"><local:message code="product.add_new" /></a></div>
<div class="box col-md-12">
    <div class="box-inner">
        <div class="box-header well" data-original-title="">
            <h2><i class="glyphicon glyphicon-list-alt"></i> <local:message code="product.list" /></h2>

            <div class="box-icon">
                <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">

            <t:message message="${message}"></t:message>
            <table class="table table-striped table-bordered responsive ">
                <thead>
                    <td>id</td>
                    <td></td>
                    <td><local:message code="product.name" /></td>
                    <td><local:message code="categories.category" /></td>
                    <td><local:message code="product.price" /></td>
                    <td></td>
                </thead>

                <c:forEach items="${products}" var="product">
                <tr>
                    <td class="center">${product.id}</td>
                    <td>
                        <c:if test="${not empty images[product.id].get(0)}">
                            <img  src="data:image/jpeg;base64,${images[product.id].get(0)}" />
                        </c:if>
                    </td>
                    <td class="center"><a href="<c:url value='${contextPath}/admin/products/edit/${product.id}' />">${product.name}</a></td>
                    <td>${product.category.name}</td>
                    <td class="center">${product.price}</td>
                    <td class="center">
                        <a href="${contextPath}/admin/products/edit/${product.id}" class="btn btn-info" role="button">
                            <i class="glyphicon glyphicon-edit icon-white"></i> <local:message code="common.edit" />
                        </a>
                        <a href="${contextPath}/admin/products/delete/${product.id}" class="btn btn-danger" role="button">
                            <i class="glyphicon glyphicon-trash icon-white"></i> <local:message code="common.delete" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div class="col-md-12 center-block">
                <div class="dataTables_paginate paging_bootstrap pagination">
                    <t:pagination page="${page}" countPage="${countPage}" link="${contextPath}/admin/products/"></t:pagination>
                </div>
            </div>
        </div>
    </div>
</div>
</t:wrapper>