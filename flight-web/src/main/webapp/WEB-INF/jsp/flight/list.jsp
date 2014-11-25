<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <jsp:body>
        <table class="table table-striped">
            <tr>
                <th>id</th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${flights}" var="flight">
                <tr>
                    <td>${flight.id}</td>
                    <td><c:out value="${flight.plane.manufacturer} - ${flight.plane.type}"/></td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>