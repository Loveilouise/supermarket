<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>超市</title>
<link href="${ctx}/resource/common/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resource/common/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resource/common/css/index.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${ctx}/resource/common/image/r___________renleipic_01/61.jpg"  style="width: 189px; height: 50px;"alt="KTV"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
<%-- 	<img src="${ctx}/resource/common/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/> --%>
</div>	
</div>
	
	<%@ include file="/common/menu.jsp" %>

</div>	

<div class="container index">
        <table width="850px" border="0" cellspacing="0">
  <tr>
    <td style="padding:30px; text-align:center"><table width="60%" border="0" cellspacing="0" style="margin-top:70px">
      <tr>
        <td style="width:98"><img src="${ctx}/resource/common/images/IconTexto_WebDev_009.jpg" width="128" height="128" /></td>
        <td style="padding-top:30px"><font style="font-weight:bold; color:#FF0000">${message}
	        <s:actionmessage/>
	        <s:actionerror/>
        </font>
        <br />
            <br />
          <a href="${ctx}/login/index.do?role=1">首页</a>
          <a href="${ctx}/login/register.do">注册</a>
          <a href="${ctx}/login/login.do?role=1">登录</a>
         </td>
      </tr>
    </table>
    <h1>&nbsp;</h1></td>
    </tr>
</table>
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
		<div class="copyright">Copyright © 2017-2017 超市  版权所有</div>
	</div>
</div>
</body>
</html>