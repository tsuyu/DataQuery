<%@page isErrorPage="true" import="java.io.*" contentType="text/html"%>
<%@page import="ercmt.cms.config.Config"%>
<!DOCTYPE html>
<img src="<%=Config.getBase_url(request)+"pnFSC.png"%>"/>
<br />
<a href="<%=Config.getBase_url(request)+"cms/ercmt/process/erole.jsp"%>"><h3>Return Back</h3></a>
<!--
Message:
<%=exception.getMessage()%>
Stack Trace:
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
%>
-->