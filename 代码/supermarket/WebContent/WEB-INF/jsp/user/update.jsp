<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <link href="${ctx}/resource/web/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/stylesheet.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/index.css" rel="stylesheet">
    <link href="icon/font-awesome.css" rel="stylesheet">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.html">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.html">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.html">
                    <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.html">
<script src="${ctx}/resource/web/js/jquery-1.10.2.js"></script>
<script src="${ctx}/resource/web/js/jquery-ui-1.10.3.js"></script>
<script src="${ctx}/resource/web/js/bootstrap.js"></script>
<script src="${ctx}/resource/web/js/flatpoint_core.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
</head>
<body>
 <div id="content"> <!-- Content start -->
      <div class="inner_content">
          <div class="widgets_area">
                <div class="row-fluid">
                    <div class="span12">
                         <div  class="daohanglink"style="">
                           <span class="daohang"></span>
                           <span>首页</span><span>></span>
                           <span>系统管理</span><span>></span>
                           <span>用户管理</span><span>></span>
                           <span>修改用户</span>
                         </div>
                        <div class="well brown">
                         <form action="${ctx}/user/exUpdate.do"  method="post">
                              <div class="form_list"><label class="lable_title">登录账号</label>
                            <input class="form_input" name="loginName" type="text" value="${obj.loginName}"/></div>
                            <div class="form_list"><label class="lable_title">用户姓名</label>
                            <input class="form_input" name="realName" type="text" value="${obj.realName}"/></div>
                                <input type="hidden" name="id"  value="${obj.id}"/>
                             <div class="form_list"><label class="lable_title">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                             <input class="form_input" name="passWord" type="password" value="${obj.passWord}"/></div>
                            <div class="form_list"><label class="lable_title">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <input type="radio" name="sex" value="1" <c:if test="${obj.sex == 1 }"> checked="checked" </c:if> >&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <input type="radio" name="sex"  value="2"<c:if test="${obj.sex == 2 }"> checked="checked"   </c:if>>&nbsp;&nbsp;女</div>
                            <div class="form_list"><label class="lable_title">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</label>
                            <input class="form_input" type="text" name="phone" value="${obj.phone}"/></div>
                            <div class="form_list"><label class="lable_title">联系地址</label>
                            <input class="form_input" type="text" name="address" value="${obj.address}"/></div>
                            <div class="form_list"><label class="lable_title">积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分</label>
                            <input class="form_input" type="text" name="jf" value="${obj.jf}"/></div>
                            <div class="form_list"><input type="submit" class="submit" value="&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;"></div>
                         </form>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>