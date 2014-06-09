<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<TITLE>注册</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<style type="text/css">
<!--
br {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
.input {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #192267;
	height: 18px;
	width: 180px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: 192267;
	border-right-color: 192267;
	border-bottom-color: 192267;
	border-left-color: 192267;
}
.text {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #192267;
}

body {
	background-color: #F1FAF7;
}
tr {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
span.required-field {
	color:red;
	font-weight:bold;
}
-->
</style>
</HEAD>

<script language="javascript">
function register_submit()
{
   if(document.reg_form.username.value=="")
   {
     alert("请填写用户名！");
	 document.reg_form.username.focus();
	 return false;
   }
   else if(document.reg_form.password.value=="")
   {
     alert("请填写密码！");
	 document.reg_form.password.focus();
	 return false;
   }
   else if(document.reg_form.password.value.length < 6)
   {
     alert("密码长度不能小于6！");
	 document.reg_form.password.focus();
	 return false;
   }
   else if(document.reg_form.password2.value=="" || document.reg_form.password2.value!=document.reg_form.password.value)
   {
     alert("两次密码输入不一致！");
	 document.reg_form.password.focus();
	 return false;
   }
   else if(document.reg_form.email.value=="")
   {
     alert("请填写邮箱！");
	 document.reg_form.password.focus();
	 return false;
   }
   else if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(document.reg_form.email.value)) {
   	 alert("邮箱格式不正确！");
	 document.reg_form.password.focus();
	 return false;
   }
   else 
   {
     document.reg_form.submit();
   }
}
</script>


<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><div align="center">
        <table width="681" border="0" align="center" cellpadding="0" cellspacing="0">
    
          <tr> 
            <td colspan="2"><img src="images/login_01.gif" width=681 height=74 alt=""></td>
          </tr>
          <tr> 
            <td width="241" rowspan="2"><img src="images/login_02.gif" width="241" height="336"></td>
            <td width="440" height="222" background="images/login_03.gif" >
            <form method = "post" action="Register" name="reg_form" onsubmit="return register_submit()">
            <table width="80%"  border="0" cellspacing="0" cellpadding="0">
            
            			<%if (session.getAttribute("error") != null) {%>
            			<font size=4 color='red'><%=session.getAttribute("error") %></font>
            			<%} %>
                <tr> 
                  <td width="27%" height="25"><div align="right" class="text"><span class="required-field">* </span>用户名：</div></td>
                  <td width="40%"><input name="username" type="text" class="input"></td>
                </tr>
                <tr> 
                  <td height="25"><p align="right" class="text"><span class="required-field">* </span>密码：</p></td>
                  <td><input name="password" type="password" class="input"></td>
                </tr>
                <tr> 
                  <td height="25"><p align="right" class="text"><span class="required-field">* </span>确认密码：</p></td>
                  <td><input name="password2" type="password" class="input"></td>
                </tr>
                <tr> 
                  <td height="25"><p align="right" class="text"><span class="required-field">* </span>邮箱：</p></td>
                  <td><input name="email" type="text" class="input"></td>
                </tr>
                <tr> 
                  <td height="35"><div align="left"> </div></td>
                  <td height="35">
                  	<input name="button" type="submit" style="width:60px;"  id="button" value="注册" >&nbsp;&nbsp;&nbsp;&nbsp;
                  	<a href="index.jsp"tabindex="9">已有账户，点此登录</a>
                  </td>
                </tr>
                
            </table></form></td>
          </tr>
          <tr>
            <td ><img src="images/login_04.gif" width="440" height="114"></td>
          </tr>
        </table>
    </div></td>
  </tr>
</table>
</BODY>
</HTML>