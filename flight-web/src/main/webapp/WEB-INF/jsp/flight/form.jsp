<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form method="post" action="${pageContext.request.contextPath}/flight/update"
                           modelAttribute="flight">
                    <form:hidden path="id"/>
                    <fieldset>
                        <legend><fmt:message key="flight.update.title"/></legend>

                        <div class="form-group">
                            <form:label path="plane"><fmt:message key="flight.plane"/></form:label>
                            <form:select path="plane" cssClass="form-control">
                                <c:forEach items="${planes}" var="plane">
                                    <option value="${plane.id}" ${plane.id == flight.plane.id ? 'selected="selected"' : ''}>${plane.manufacturer}
                                        - ${plane.type}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="plane" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="departureTime"><fmt:message key="flight.departure_time"/></form:label>
                            <form:input path="departureTime" cssClass="form-control"/>
                            <form:errors path="departureTime" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="from"><fmt:message key="flight.airport.to"/></form:label>
                            <form:select path="from" cssClass="form-control">
                                <c:forEach items="${airports}" var="airport">
                                    <option value="${airport.id}" ${airport.id == flight.from.id ? 'selected="selected"' : ''}>${airport.name}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="from" cssClass="error"/>

                        </div>

                        <div class="form-group">
                            <form:label path="arrivalTime"><fmt:message key="flight.arrival_time"/></form:label>
                            <form:input path="arrivalTime" cssClass="form-control"/>
                            <form:errors path="arrivalTime" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="to"><fmt:message key="flight.airport.to"/></form:label>
                            <form:select path="to" cssClass="form-control">
                                <c:forEach items="${airports}" var="airport">
                                    <option value="${airport.id}" ${airport.id == flight.to.id ? 'selected="selected"' : ''}>${airport.name}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="to" cssClass="error"/>
                        </div>


                        <div class="form-group">
                            <form:label path="stewards"><fmt:message key="flight.stewards"/></form:label>
                            <form:select path="stewards" multiple="multiple" cssClass="form-control">
                                <c:forEach items="${stewards}" var="steward">
                                    <option value="${steward.id}">${steward.name}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="stewards" cssClass="error"/>
                        </div>


                        <input type="submit" value="<fmt:message key='generic.save'/>" class="btn btn-primary">
                    </fieldset>
                </form:form>
            </div>
        </div>

    </jsp:body>
</t:layout>