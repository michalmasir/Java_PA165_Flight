<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:message var="title" key="airport.label.title"/>
<my:layout>
    <jsp:body>
        <h1><fmt:message key="airport.label.list"/></h1>
        <table class="table table-striped">
            <tr>
                <th><fmt:message key="airport.label.id"/></th>
                <th><fmt:message key="airport.label.name"/></th>
                <th><fmt:message key="airport.label.state"/></th>
                <th><fmt:message key="airport.label.city"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${airports}" var="airport">
                <tr>
                    <td>${airport.id}</td>
                    <td><c:out value="${airport.name}" /></td>
                    <td><c:out value="${airport.state}" /></td>
                    <td><c:out value="${airport.city}" /></td>
                    <td><a href="<s:url value='/airport/update/${airport.id}'/>"><fmt:message key="generic.update"/></a></td>
                    <td><a href="<s:url value='/airport/delete/${airport.id}'/>"><fmt:message key="generic.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</my:layout>


