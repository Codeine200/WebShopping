<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="List of Products">

    <div class="additional_menu"><a href="${contextPath}/admin/categories/new"><local:message code="categories.add_new" /></a></div>
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-list"></i> <local:message code="categories.list" /></h2>

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
                    <td>ID</td><td><local:message code="categories.name" /></td><td><local:message code="categories.count_products" /></td><td></td>
                    </thead>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td class="center">${category.id}</td>
                            <td class="center"><a href="<c:url value='${contextPath}/admin/categories/edit/${category.id}' />">${category.name}</a></td>
                            <td class="center">${category.getProductList().size()}</td>
                            <td class="center">
                                <a href="${contextPath}/admin/categories/edit/${category.id}" class="btn btn-info" role="button">
                                    <i class="glyphicon glyphicon-edit icon-white"></i> <local:message code="common.edit" />
                                </a>
                                <a href="${contextPath}/admin/categories/delete/${category.id}" class="btn btn-danger" role="button">
                                    <i class="glyphicon glyphicon-trash icon-white"></i> <local:message code="common.delete" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                <div class="col-md-12 center-block">
                    <div class="dataTables_paginate paging_bootstrap pagination">
                        <t:pagination page="${page}" countPage="${countPage}" link="products/list"></t:pagination>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:wrapper>