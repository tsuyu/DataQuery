<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dm.client.DataMapp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
DataQuery dq = new DataQuery("Index.jsp");
String params[] = new String[0];
String query = "SELECT rl.rl_role FROM role rl ";
ArrayList<ArrayList<String>> data = dq.getData(query, params);
dm.close();
out.print(data.get(0).get(0));
%>
</body>
</html>