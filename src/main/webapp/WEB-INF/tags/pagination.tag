<%@tag description="display pagination" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" %>
<%@ attribute name="countPage" required="true" %>
<%@ attribute name="link" required="true" %>

<ul class="pagination pagination-centered">

    <c:url value="${link}" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:choose>
        <c:when test="${page > 1}">
            <li class="prev"><a href="<c:out value="${prev}" />">&larr; Prev</a></li>
        </c:when>
        <c:otherwise>
            <li class="prev disabled"><a href="#" >&larr; Prev</a></li>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="1" end="${countPage}" step="1" varStatus="i">
        <c:url value="${link}" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <c:choose>
            <c:when test="${page == i.index}">
                <li class="active">
                    <a href="#">${i.index}</a>
                </li>
            </c:when>
            <c:otherwise>
                <li><a href='<c:out value="${url}" />'>${i.index}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="${link}" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:choose>
        <c:when test="${page + 1 <= countPage}">
            <li class="next"><a href="<c:out value="${next}" />">Next &rarr;</a></li>
        </c:when>
        <c:otherwise>
            <li class="next disabled"><a href="#" >Next &rarr;</a></li>
        </c:otherwise>
    </c:choose>
</ul>
