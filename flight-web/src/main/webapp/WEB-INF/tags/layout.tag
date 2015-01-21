<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cc" %>

<%@attribute name="section"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main_${pageContext.response.locale}.js" ></script>
</head>
<body>

<div class="container">
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">PA165</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="<cc:if test="${section == 'flights'}">active</cc:if>"><a href="<c:url value='/flight/list'/>"><fmt:message key="flight.menu"/></a></li>
                    <li class="<cc:if test="${section =='planes'}">active</cc:if>"><a href="<c:url value='/plane/list'/>"><fmt:message key="plane.menu"/></a></li>
                    <li class="<cc:if test="${section =='airports'}">active</cc:if>"><a href="<c:url value='/airport/list'/>"><fmt:message key="airport.menu"/></a></li>
                    <li class="<cc:if test="${section =='stewards'}">active</cc:if>"><a href="<c:url value='/steward/list'/>"><fmt:message key="steward.menu"/></a></li>
                    <li class="<cc:if test="${section =='users'}">active</cc:if>"><a href="<c:url value='/user/list'/>"><fmt:message key="user.menu"/></a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class="panel">
        <cc:if test="${not empty message}">
            <div class="message">
                <p class="bg-info"><cc:out value="${message}"/></p>
            </div>
        </cc:if>
        <jsp:doBody/>
    </div>
</div>
</body>
</html>