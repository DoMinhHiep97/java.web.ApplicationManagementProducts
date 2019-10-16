<%--
  Created by IntelliJ IDEA.
  User: 19tha
  Date: 10/16/2019
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name:</td>
        <td>${requestScope["product"].getName()}</td>
    </tr>
    <tr>
        <td>Information:</td>
        <td>${requestScope["product"].getInformation()}</td>
    </tr>
    <tr>
        <td>Producer:</td>
        <td>${requestScope["product"].getProducer()}</td>
    </tr>
    <tr>
        <td>Price:</td>
        <td>${requestScope["product"].getPrice()}</td>
    </tr>
    <tr>
        <td>Id:</td>
        <td>${requestScope["product"].getId()}</td>
    </tr>
</table>
</body>
</html>
