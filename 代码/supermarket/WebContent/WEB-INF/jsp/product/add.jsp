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
<title>新增一级分类</title>
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
                            <span>商品管理</span><span>></span>
                           <span>修改商品</span>
                         </div>
                        <div class="well brown">
                         <form action="${ctx}/product/exAdd.do"  method="post" enctype="multipart/form-data">
                            <div class="form_list"><label class="lable_title">商品名称</label>
                            <input class="form_input" name="name" type="text"/></div>
                             <input class="form_input" name="id" type="hidden"/>
                            <div class="form_list"><label class="lable_title">商品价格</label>
                            <input class="form_input" name="price" type="text"/></div>
                             <div class="form_list"><label class="lable_title">商品图片</label>
                           <input class="form_input" type="file" name="file" id=""></div>
                            <div class="form_list"><label class="lable_title">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
                            <input class="form_input" type="text" name="remark"/></div>
                              <div class="form_list"><label class="lable_title">二级分类</label>&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="categorySecId" >
                              <c:forEach  items="${categorySecList}" var="cl" varStatus="l">
                               <option value="${cl.id }">${cl.scname }</option>
                              </c:forEach>
				            </select>
                            </div>
                            <div  class="form_list"><input type="submit" class="submit" value="&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;"></div>
                         </form>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>