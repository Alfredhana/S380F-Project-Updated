<!DOCTYPE html>
<html>
    <head>
        <title>Add Poll</title>
    </head>
       <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Add Poll</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollForm">
            <form:label path="topic">Topic:</form:label><br/>
            <form:textarea path="topic" type="body" rows="5" cols="30" /><br/>
            <form:label path="optionone">Option 1</form:label><br/>
            <form:input type="text" path="optionone" /><br/><br/>
            <form:label path="optiontwo">Option 2</form:label><br/>
            <form:input type="text" path="optiontwo" /><br/><br/>
            <form:label path="optionthree">Option 3</form:label><br/>
            <form:input type="text" path="optionthree" /><br/><br/>
            <form:label path="optionfour">Option 4</form:label><br/>
            <form:input type="text" path="optionfour" /><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
        <a href="<c:url value="/user" />">Return to list users</a>
    </body>
</html>
