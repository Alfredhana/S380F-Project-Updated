<!DOCTYPE html>
<html>
    <head>
        <title>Other Page</title>
    </head>
    <body>
        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />">Login</a>   
            <a href="<c:url value="/register" />">Register</a><br /><br />
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br /><br />
            </form>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Admin Management</a><br /><br />
        </security:authorize>
        <a href="<c:url value="/lecture" />">Lecture</a><br>
        <a href="<c:url value="/lab" />">Lab</a><br>
        <a href="<c:url value="/other" />">Other</a><br>
        <h2>Other Tickets</h2>      
        <security:authorize access="isAuthenticated()">
            <a href="<c:url value="/ticket/create" />">Create a Ticket</a><br /><br />
        </security:authorize>
        <c:choose>
            <c:when test="${fn:length(ticketDatabase) == 0}">
                <i>There are no tickets in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ticketDatabase}" var="ticket">
                    <c:if test= "${ticket.category == 'Other'}">
                        Ticket ${ticket.id}:
                        <a href="<c:url value="/ticket/view/${ticket.id}" />">
                            <c:out value="${ticket.subject}" /></a>
                        (customer: <c:out value="${ticket.customerName}" />)
                        <security:authorize access="isAuthenticated()">
                            <security:authorize access="hasRole('ADMIN') or 
                                                principal.username=='${ticket.customerName}'">
                                [<a href="<c:url value="/ticket/edit/${ticket.id}" />">Edit</a>]
                            </security:authorize>
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/ticket/delete/${ticket.id}" />">Delete</a>]
                        </security:authorize>
                        <br /><br />
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
