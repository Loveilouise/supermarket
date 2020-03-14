<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>会员注册</title>
<link href="${ctx}/resource/common/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resource/common/css/register.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/resource/web/js/jquery-1.10.2.js"></script>
<script>
</script>
</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
					<img src="${ctx}/resource/common/image/r___________renleipic_01/61.jpg"  style="width: 189px; height: 50px;"alt="KTV"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
<%-- 					<img src="${ctx}/resource/common/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"> --%>
</div>	</div>
	
	<%@ include file="/common/menu.jsp" %>

</div>	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>会员注册</strong>USER REGISTER
					</div>
					<div>
						<s:actionerror />
					</div>
<%-- 					<form id="registerForm" action="${ctx}/user_register.do"  method="post" novalidate="novalidate" onsubmit="return checkForm();"> --%>
						<table>
							<tbody>
							<tr>
								<th>
									<span class="requiredField">*</span>登录账号:
								</th>
								<td>
									<input type="text" id="loginName" name="loginName" class="text" maxlength="20" onblur="checkUsername()"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>真实姓名:
								</th>
								<td>
									<input type="text" id="realName" name="realName" class="text" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="passWord" name="passWord" class="text" maxlength="20" autocomplete="off"/>
								</td>
							</tr>
									
									<tr>
										<th>
											电话:
										</th>
										<td>
												<input type="text" id="phone" name="phone" class="text" />
										</td>
									</tr>
									<tr>
										<th>
											性别:
										</th>
										<td>
										男:<input type="radio" id="sex" name="sex" value="1" />
				                         &nbsp;&nbsp; &nbsp;&nbsp;  女:<input type="radio" name="sex" value="2"  />
										</td>
									</tr>
									<tr>
										<th>
											地址:
										</th>
										<td>
											<input type="text" id="address" name="address" class="text" maxlength="200"/>
										</td>
									</tr>
										<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="submit" class="submit" value="注册" onclick="register()">
								</td>
							</tr>
						          </tbody>
						</table>
						<div class="login">
<div class="ad">
					<dl>
	<dt>
		注册即享受
	</dt>
	<dd>
		正品保障、正规发票
	</dd>
	<dd>
		货到付款、会员服务
	</dd>
	<dd>
		自由退换、售后上门
	</dd>
</dl>
</div>							<dl>
								<dt>已经拥有账号了？</dt>
								<dd>
									立即登录即可体验在线购物！
									<a href="${ctx}/login/login.do?role=1">立即登录</a>
								</dd>
							</dl>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${ctx}/resource/common/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a>关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2017-2017  超市  版权所有</div>
	</div>
</div>
<div id="_my97DP" style="position: absolute; top: -1970px; left: -1970px;"><iframe style="width: 190px; height: 191px;" src="./会员注册 - Powered By Mango Team_files/My97DatePicker.htm" frameborder="0" border="0" scrolling="no"></iframe></div></body>
<script>
//注册验证
function register(){
	   var loginName= $("#loginName").val();
	   var realName= $("#realName").val();
		var passWord = $("#passWord").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		var sex = $("#sex").val();
 		$.ajax({
              url: ctx+'/user/register.json',
 			type: 'POST',
 			dataType: 'json',
 		    data: {loginName:loginName,passWord:passWord,realName:realName,phone:phone,address:address,sex:sex
 		    },
 			async: false,
 			success : function(data) {
 				if(data.result == 1){
 					 alert("注册成功");
 					location.href='${ctx}/login/login.do?role=1';
 					}else{
 					     if(data.result == 2){
 					    	 alert("该用户已经注册过");
 					     }
 					}
 			},
 				error : function() {
 					alert("error");
 				}
 		});
   }
</script>



</html>