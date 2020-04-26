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
            [<a href="<c:url value="/ticket/createReply/${ticket.id}" />">Create Reply</a>]
        
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
        <h2>Reply</h2><br /><br />
        <c:if test="${fn:length(reply) > 0}">
            <i>Customer Name - <c:out value="${reply.reply_author}" /></i><br /><br />
            <c:out value="${reply.reply_content}" /><br /><br />
            <c:if test="${fn:length(reply.replyAttachments) > 0}">
                Reply Attachments:
                <c:forEach items="${reply.replyAttachments}" var="replyAttachment" varStatus="status">
                    <c:if test="${!status.first}">, </c:if>
                    <a href="<c:url value="/ticket/${ticket.id}/replyAttachment/${replyAttachment.name}" />">
                        <c:out value="${replyAttachment.name}" /></a>
                </c:forEach><br /><br />
            </c:if>
        </c:if>
    </body>
</html>
