<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="users">
    <jsp:body>
        <div class="page-header">
            <h1><fmt:message key="user.list"/></h1>
        </div>
        <table class="table table-striped data-table">
            <thead>
            <tr>
                <th><fmt:message key="user.username"/></th>
                <th><fmt:message key="user.userRole"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.userRole}"/></td>
                    <td><a href="<s:url value='/user/delete/${user.username}'/>"><fmt:message key="generic.delete"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="<s:url value='/user/create/'/>" class="right btn btn-primary"><fmt:message key="generic.create"/></a>
    </jsp:body>
</t:layout>