<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 64974
  Date: 2018/5/25
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="conn" scope="page" class="com.tools.ConnDB"/>
<jsp:useBean id="ins_member" scope="page" class="com.dao.MemberImpl"/>
<jsp:useBean id="member" scope="page" class="com.model.Member">
    <jsp:setProperty name="member" property="*"/>
</jsp:useBean>


<html>
<head>
    <title>信息</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String username = member.getUsername();
    ResultSet rs = conn.executeQuery("select * from tb_member where username='" + username + "'");
    if (rs.next()) {
        out.println("<script language='javascript'>alert('该账号已经存在，请重新注册!');window.location.href='register.jsp';</script>");
    }else{
        int ret=0;
        ret=ins_member.insert(member);
        if (ret!=0){
            session.setAttribute("username",username);
            out.println("<script language='javascript'>alert('会员注册成功!');window.location.href='login.jsp';</script>");
        }else {
            out.println("<script language='javascript'>alert('会员注册失败!');window.location.href='register.jsp';</script>");
        }
    }
%>

</body>
</html>
