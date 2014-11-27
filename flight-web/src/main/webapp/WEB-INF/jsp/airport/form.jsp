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
                <form:form method="post" action="${pageContext.request.contextPath}/airport/update" modelAttribute="airport">
                    <form:hidden path="id"/>
                    <fieldset>
                        <legend><fmt:message key="airport.label.update.title"/></legend>

                        <div class="form-group">
                            <form:label path="name"><fmt:message key="airport.label.name"/></form:label>
                            <form:input path="name" cssClass="form-control"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>

               <div class="form-group">
                            <form:label path="state"><fmt:message key="airport.label.state"/></form:label>
                            <form:input path="state" cssClass="form-control"/>
                            <form:errors path="state" cssClass="error"/>
                        </div>

               <div class="form-group">
                            <form:label path="city"><fmt:message key="airport.label.city"/></form:label>
                            <form:input path="city" cssClass="form-control"/>
                            <form:errors path="city" cssClass="error"/>
                        </div>
                        <input type="submit" value="<fmt:message key='generic.save'/>" class="btn btn-primary">
                    </fieldset>
                </form:form>
            </div>
        </div>

    </jsp:body>
</t:layout>