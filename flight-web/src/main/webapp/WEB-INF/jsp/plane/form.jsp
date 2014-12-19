<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="planes">
    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form method="post" action="${pageContext.request.contextPath}/plane/update"
                           modelAttribute="plane">
                    <form:hidden path="id"/>
                    <fieldset>
                        <legend><fmt:message key="plane.update.title"/></legend>

                        <div class="form-group">
                            <form:label path="id"><fmt:message key="plane.id"/></form:label>
                            <form:input readonly="true" path="id" cssClass="form-control"/>
                            <form:errors path="id" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="manufacturer"><fmt:message key="plane.manufacturer"/></form:label>
                            <form:input path="manufacturer" cssClass="form-control"/>
                            <form:errors path="manufacturer" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="type"><fmt:message key="plane.type"/></form:label>
                            <form:input path="type" cssClass="form-control"/>
                            <form:errors path="type" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="passangerSeatsCount"><fmt:message key="plane.passangerSeatsCount"/></form:label>
                            <form:input path="passangerSeatsCount" cssClass="form-control"/>
                            <form:errors path="passangerSeatsCount" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="staffSeatsCount"><fmt:message key="plane.staffSeatsCount"/></form:label>
                            <form:input path="staffSeatsCount" cssClass="form-control"/>
                            <form:errors path="staffSeatsCount" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="tankCapacity"><fmt:message key="plane.tankCapacity"/></form:label>
                            <form:input path="tankCapacity" cssClass="form-control"/>
                            <form:errors path="tankCapacity" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="fuelLeft"><fmt:message key="plane.fuelLeft"/></form:label>
                            <form:input path="fuelLeft" cssClass="form-control"/>
                            <form:errors path="fuelLeft" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="lastRevisionTime"><fmt:message key="plane.lastRevisionTime"/></form:label>
                            <form:input path="lastRevisionTime" cssClass="form-control"/>
                            <form:errors path="lastRevisionTime" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="totalFlightTime"><fmt:message key="plane.totalFlightTime"/></form:label>
                            <form:input path="totalFlightTime" cssClass="form-control"/>
                            <form:errors path="totalFlightTime" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="totalFlightDistance"><fmt:message key="plane.totalFlightDistance"/></form:label>
                            <form:input path="totalFlightDistance" cssClass="form-control"/>
                            <form:errors path="totalFlightDistance" cssClass="error"/>
                        </div>

                        <input type="submit" value="<fmt:message key='generic.save'/>" class="btn btn-primary">
                    </fieldset>
                </form:form>
            </div>
        </div>

    </jsp:body>
</t:layout>