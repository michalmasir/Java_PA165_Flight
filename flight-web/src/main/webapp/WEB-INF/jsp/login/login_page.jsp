<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout section="flights">
    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-body">

                <h3><fmt:message key="login"/></h3>

                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                    <div class="alert alert-danger validation_error" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only"> <fmt:message key="generic.error"/>:</span>
                        <fmt:message key="login.error"/>
                    </div>
                </c:if>

                <form name='loginForm' action="<c:url value='j_spring_security_check' />"
                      method='POST'>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-group">
                        <label for="username"><fmt:message key="login.username"/></label>
                        <input type="text" class="form-control" id="username" name='username'
                               placeholder="<fmt:message key="login.username.placeholder"/>">
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="login.password"/></label>
                        <input type="password" class="form-control" id="password" name='password'
                               placeholder="<fmt:message key="login.password.placeholder"/>">
                    </div>

                    <button name="submit" type="submit" value="submit" class="btn btn-default"><fmt:message
                            key="login.submit"/></button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout>

