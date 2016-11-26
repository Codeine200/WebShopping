<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Exception</title>
    <!-- CUSTOM -->
    <link href="${contextPath}/includes/css/custom.css" rel="stylesheet">
</head>

<body>

    <div class="wrapper">
        <h1 class="exception">${exception.getMessage()}</h1>
        <h2 class="StackTrace">
            <c:forEach items="${exception.getStackTrace()}" var="item">
                <div>${item}</div>
            </c:forEach>
        </h2>
      <div class="content  fit-vid  vid is-loaded">
        <div class="fluid-width-video-wrapper" style="padding-top: 56.25%;"><iframe src="http://www.youtube.com/embed/SIaFtAKnqBU?vq=hd720&amp;rel=0&amp;showinfo=0&amp;controls=0&amp;iv_load_policy=3&amp;loop=1&amp;playlist=SIaFtAKnqBU&amp;modestbranding=1&amp;autoplay=1" frameborder="0" webkitallowfullscreen="" allowfullscreen="" id="fitvid641379"></iframe></div>
      </div>

    </div>
</body>
</html>
