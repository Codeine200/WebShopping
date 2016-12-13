<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="local" uri="http://www.springframework.org/tags" %>

<t:template title="Page not found">

    <div class="container" id="container">
        <div class="fallback fillParent vAligner text-center" style="display: block;">
            <div class="vItem">
                <h1>404</h1>
                <p><local:message code="common.page_not_found" /></p>
            </div>
        </div>
    </div>

</t:template>

