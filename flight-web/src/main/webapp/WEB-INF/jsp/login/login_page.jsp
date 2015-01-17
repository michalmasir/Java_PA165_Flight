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

                <h3>PA165 Login</h3>

                <c:if test="${param.error}">
                    <div style="color:red">
                        Invalid credentials
                    </div>
                </c:if>
                <form name='loginForm' action="<c:url value='j_spring_security_check' />"
                      method='POST'>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table>
                        <tr>
                            <td>User:</td>
                            <td><input type='text' name='username' value=''>
                            </td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type='password' name='password'/>
                            </td>
                        </tr>
                        <tr>
                            <td><input name="submit" type="submit" value="submit"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout>

