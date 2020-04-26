<!DOCTYPE html>
<html>
    <head>
        <title>Reply</title>
    </head>
    <body>
        <h2>Reply</h2>
        
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="replyForm">
            <form:label path="reply_content">Reply:</form:label><br/>
            <form:textarea path="reply_content" rows="5" cols="30" /><br/><br/>
            <b>Reply Attachments</b><br/>
            <input type="file" name="replyAttachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form><br />
        <a href="<c:url value="/ticket/list" />">Return to the ticket</a>
    </body>
</html>