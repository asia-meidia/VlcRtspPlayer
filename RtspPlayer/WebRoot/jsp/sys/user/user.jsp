<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
	String ctx = request.getContextPath();
%>
<script type="text/javascript">
<!--
var ctx = '<%=ctx%>';
//-->
</script>
<div id='yhxx_search'></div>
<div id='yhxx_grid'></div>
 <script type="text/javascript" src="<%=ctx%>/jsp/sys/user/user.js"></script>
