<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="users">
    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form method="post" action="${pageContext.request.contextPath}/user/update" modelAttribute="user">
                    <fieldset>
                        <legend><fmt:message key="user.update.title"/></legend>

                        <div class="form-group">
                            <form:label path="username"><fmt:message key="user.username"/> </form:label>
                            <form:input path="username" cssClass="form-control"/>
                            <form:errors path="username" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="password"><fmt:message key="user.password"/> </form:label>
                            <form:input path="password" cssClass="form-control"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <form:label path="userRole"><fmt:message key="user.userRole"/></form:label>
                            <form:select path="userRole" cssClass="form-control">
                                <c:forEach items="${userRoles}" var="userRole">
                                    <option value="${userRole}" ${userRole == user.userRole ? 'selected="selected"' : ''}>${userRole}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="userRole" cssClass="error"/>
                        </div>

                        <input type="submit" value="<fmt:message key='generic.save'/>" class="btn btn-primary">
                    </fieldset>
                </form:form>

            </div>
        </div>
    </jsp:body>
</t:layout>