<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 64974
  Date: 2018/5/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="coon" scope="page" class="com.tools.ConnDB"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<%
    String userName = request.getParameter("username");
    String checkCode = request.getParameter("checkCode");
    if (checkCode.equals(session.getAttribute("randCheckCode").toString())) {
       try {
           ResultSet rs = coon.executeQuery("select * from tb_member where username='" + userName + "'");
           if (rs.next()) {
               String PWD = request.getParameter("PWD");
               if (PWD.equals(rs.getString("password"))) {
                   session.setAttribute("username", userName);
                   response.sendRedirect("index.jsp");
               } else {
                   out.print("<script language='javascript'>alert('您输入的用户名或密码错误,请与管理员联系!');window.location.href='login.jsp';</script>");
               }
           } else {
               out.print("<script language='javascript'>alert('您输入的用户名或密码错误,或您的账户已被冻结,请与管理员联系!');window.location.href='login.jsp';</script>");
           }
       }catch (Exception e){
           out.print("<script language='javascript'>alert('您的操作有误!');window.location.href='login.jsp';</script>");
       }
       coon.close();
    }else {
        out.print("<script language='javascript'>alert('您输入的验证码有误!');window.location.href='history.jsp';</script>");

    }
%>

</body>
</html>
