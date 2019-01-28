<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red"><h2 align="center">Sms Hub</h2></font>
<form action="sendSmsUsingjson">
<table >
${failureMsg}</br>
${successMsg}</br>
Failed Numbers are:${mobilefails }</br>
<tr><td>Mobile Number</td><td>:</td><td><input type="text" name="mobile"/></td></tr></br>
<tr><td>Text Message</td><td>:</td><td><textarea rows="8" cols="22" name="text"></textarea></td></tr></br>
</table>
<input type="submit" value="Send Sms">
</form>
</body>
</html>