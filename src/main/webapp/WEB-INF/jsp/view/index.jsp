<!DOCTYPE html>
<html>
    <head>
        <title>Course Discussion Forum</title>
    </head> 
    <body>
        <spring:message code="label.mainHello"/><br /><a href="<c:url value="http://localhost:8080/Lab10/?lang=en" />">English</a> | <a href="<c:url value="http://localhost:8080/Lab10/?lang=hk" />">Traditional Chinese</a>
        <br /><br />
        <security:authorize access="isAuthenticated() ">              
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form><br />
            Welcome, <security:authentication property="principal.username" />!!<br /><br />            
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />"><spring:message code="label.login"/></a>   
            <a href="<c:url value="/register" />"><spring:message code="label.register"/></a><br /><br />
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Admin Management</a><br />
        </security:authorize>
            <h2><spring:message code="label.poll"/></h2>
        
        <c:choose>
            <c:when test="${fn:length(pollDB) == 0}">
                <p> There are no active poll yet </p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${pollDB}" var="poll">
                    Poll Topic: ${poll.topic}<br><br/>
                    <form:form method="POST" modelAttribute="displayPoll">
                        <c:if test="${poll.optionone != ''}">
                            Option One : ${poll.optionone} 
                            Vote count :
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${voteset}" var="entry">
                                <c:if test="${entry.choice == 1}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                            </c:forEach>
                            ${count}<br/>
                            <security:authorize access="isAuthenticated() ">
                                <form:radiobutton path="choice" value="1"/><br/><br/>
                            </security:authorize>
                        </c:if>
                        <c:if test="${poll.optiontwo != ''}">
                            Option Two : ${poll.optiontwo}
                            Vote count :
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${voteset}" var="entry">
                                <c:if test="${entry.choice == 2}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                            </c:forEach>
                            ${count}<br/>
                            <security:authorize access="isAuthenticated() ">
                                <form:radiobutton path="choice" value="2"/><br/><br/>
                            </security:authorize>
                        </c:if>
                        <c:if test="${poll.optionthree != ''}">
                            Option Three : ${poll.optionthree} 
                            Vote count :
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${voteset}" var="entry">
                                <c:if test="${entry.choice == 3}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                            </c:forEach>
                            ${count}<br/>
                            <security:authorize access="isAuthenticated() ">
                                <form:radiobutton path="choice" value="3"/><br/><br/>
                            </security:authorize>
                        </c:if>
                        <c:if test="${poll.optionfour != ''}">
                            Option Four : ${poll.optionfour}
                            Vote count :
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${voteset}" var="entry">
                                <c:if test="${entry.choice == 4}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                            </c:forEach>
                            ${count}<br/>
                            <security:authorize access="isAuthenticated() ">
                                <form:radiobutton path="choice" value="4"/><br/><br/>
                            </security:authorize>
                        </c:if>
                        <security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                            <input type="submit" value="Vote"/><br/><br/>
                        </security:authorize>
                    </form:form>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <security:authorize access="isAuthenticated() ">
            <a href="<c:url value="/ticket/create" />">Create a Ticket</a><br /><br />
        </security:authorize>
        <a href="<c:url value="/lecture" />"><spring:message code="label.lecture"/></a><br>
        <a href="<c:url value="/lab" />"><spring:message code="label.lab"/></a><br>
        <a href="<c:url value="/other" />"><spring:message code="label.other"/></a><br>
        
    </body>
</html>
