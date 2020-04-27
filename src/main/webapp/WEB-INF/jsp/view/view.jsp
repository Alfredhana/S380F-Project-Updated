<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        <h2>Ticket #${ticket.id}: <c:out value="${ticket.subject}" /></h2>
        <security:authorize access="isAuthenticated()">
            <security:authorize access="hasRole('ADMIN') or principal.username=='${ticket.customerName}'">
                [<a href="<c:url value="/ticket/edit/${ticket.id}" />">Edit</a>]
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                [<a href="<c:url value="/ticket/delete/${ticket.id}" />">Delete</a>]
            </security:authorize>
        </security:authorize>
        <br /><br />
        <i>Customer Name - <c:out value="${ticket.customerName}" /></i><br /><br />
        <c:out value="${ticket.body}" /><br /><br />
        <c:if test="${fn:length(ticket.attachments) > 0}">
            Attachments:
            <security:authorize access="isAuthenticated()">
                    <c:forEach items="${ticket.attachments}" var="attachment" varStatus="status">
                    <c:if test="${!status.first}">, </c:if>
                    <a href="<c:url value="/ticket/${ticket.id}/attachment/${attachment.name}" />">
                        <c:out value="${attachment.name}" /></a>
                </c:forEach><br /><br />
            </security:authorize>
        </c:if>
        <a href="<c:url value="/ticket/list" />">Return to list tickets</a><br /><br />
        <h2>Reply</h2>
        <security:authorize access="isAuthenticated()">
            [<a href="<c:url value="/ticket/createReply/${ticket.id}" />">Create Reply</a>]<br /><br />
        </security:authorize>
            <c:if test="${fn:length(ticket.reply) > 0}">
                <c:forEach items="${ticket.reply}" var="r" varStatus="status">
                    <i>Customer Name - ${r.replyauthor}</i><br /><br />
                    <c:out value="${r.replycontent}" /><br /><br />
                    <c:if test="${fn:length(r.replyAttachments) > 0}">
                        Reply Attachments:
                        <c:forEach items="${r.replyAttachments}" var="replyAttachment" varStatus="status">
                            <c:if test="${!status.first}">, </c:if>
                            <a href="<c:url value="/ticket/${ticket.id}/replyAttachment/${replyAttachment.rname}" />">
                                <c:out value="${replyAttachment.rname}" /></a>
                        </c:forEach><br /><br />                    
                    </c:if>
                    <hr/>
                </c:forEach>
            </c:if>
    </body>
</html>
