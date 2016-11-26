<%@tag description="display message or error" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="message" required="true" type="com.wizardjava.models.Message"%>
<c:if test="${message != null && !message.getMessage().isEmpty()}">
    <div class="alert alert-${message.getType().toString().toLowerCase()}">
     ${message.getMessage()}
    </div>
</c:if>