<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <jsp:body>
        <div class="page-header">
            <h1><fmt:message key="flight.list"/></h1>
        </div>
        <table class="table table-striped">
            <tr>
                <th>id</th>
                <th><fmt:message key="flight.from_airport"/></th>
                <th><fmt:message key="flight.departure_date"/></th>
                <th><fmt:message key="flight.to_airport"/></th>
                <th><fmt:message key="flight.arrival_date"/></th>
                <th><fmt:message key="flight.plane"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${flights}" var="flight">
                <tr>
                    <td>${flight.id}</td>
                    <td>${flight.from.name}</td>
                    <td>${flight.departureTime}</td>
                    <td>${flight.to.name}</td>
                    <td>${flight.arrivalTime}</td>
                    <td><c:out value="${flight.plane.manufacturer} - ${flight.plane.type}"/></td>
                    <td><a href="<s:url value='/flight/update/${flight.id}'/>"><fmt:message key="generic.update"/></a></td>
                    <td><a href="<s:url value='/flight/delete/${flight.id}'/>"><fmt:message key="generic.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>