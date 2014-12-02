<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="stewards">
    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form method="post" action="${pageContext.request.contextPath}/steward/update" modelAttribute="steward">
                    <form:hidden path="id"/>
                    <fieldset>
                        <legend><fmt:message key="steward.update.title"/> </legend>

                        <div class="form-group">
                            <form:label path="firstName"><fmt:message key="steward.firstName"/> </form:label>
                            <form:input path="firstName" cssClass="form-control"/>
                            <form:errors path="firstName" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="lastName"><fmt:message key="steward.lastName"/> </form:label>
                            <form:input path="lastName" cssClass="form-control"/>
                            <form:errors path="lastName" cssClass="error"/>
                        </div>
                        <input type="submit" value="<fmt:message key='generic.save'/>" class="btn btn-primary">
                    </fieldset>
                </form:form>

            </div>
        </div>
    </jsp:body>
</t:layout>