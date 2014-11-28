<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="stewards">
    <jsp:body>
        <div class="page-header">
            <h1>
                <fmt:message key="steward.list"/>
            </h1>
        </div>

        <table class="table table-striped data-table">
            <thead>
            <tr>
                <th>id</th>
                <th><fmt:message key="steward.firstName"/> </th>
                <th><fmt:message key="steward.lastName"/> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${stewards}" var="steward">
                <tr>
                    <td>${steward.id}</td>
                    <td>${steward.firstName}</td>
                    <td>${steward.lastName}</td>
                    <td><a href="<s:url value='/steward/update/${steward.id}'/>"><fmt:message key="generic.update"/></a></td>
                    <td><a href="<s:url value='/steward/delete/${steward.id}'/>"><fmt:message key="generic.delete"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="<s:url value='/steward/create/'/>" class="right btn btn-primary"><fmt:message key="generic.create"/></a>
    </jsp:body>
</t:layout>