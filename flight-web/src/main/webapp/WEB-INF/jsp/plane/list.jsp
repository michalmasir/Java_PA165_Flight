<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="planes">
    <jsp:body>
        <div class="page-header">
            <h1><fmt:message key="plane.list"/></h1>
        </div>
        <table class="table table-striped data-table">
            <thead>
            <tr>
                <th>id</th>
                <th><fmt:message key="plane.type"/></th>
                <th><fmt:message key="plane.manufacturer"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${planes}" var="plane">
                <tr>
                    <td>${plane.id}</td>
                    <td>${plane.type}</td>
                    <td>${plane.manufacturer}</td>
                    <td><a href="<s:url value='/plane/update/${plane.id}'/>"><fmt:message key="generic.update"/></a>
                    </td>
                    <td><a href="<s:url value='/plane/delete/${plane.id}'/>"><fmt:message key="generic.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="<s:url value='/plane/create/'/>" class="right btn btn-primary"><fmt:message key="generic.create"/></a>

    </jsp:body>
</t:layout>