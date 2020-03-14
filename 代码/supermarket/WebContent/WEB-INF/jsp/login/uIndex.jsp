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
<link href="${ctx}/resource/common/css/index.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/resource/common/js/jquery-1.8.3.js"></script>


</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${ctx}/resource/common/image/r___________renleipic_01/61.jpg"  style="width: 189px; height: 50px;"alt="超市"/>
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
        <form action="${ctx}/login/index.do?role=1" method="post">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
			<div class="title" style="width:1000px">
						<strong>商品搜索</strong>
					 <input type="text" name="name" id="name"  value="${product.name}" style="margin-top: -4px;margin-left: 20px;"/>
					   <button type="submit" style="margin-left: 50px ;margin-top: 8px; cursor: pointer; background-color: #fff">查询</button>
			</div>
			</div>
		</div>
        </form>
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title" style="width:1000px">
						<strong>商品展示</strong>
						</div>
						<ul class="tabContent" style="display: block;">
						 <c:forEach items="${pagers.datas}" var="product" varStatus="l">
									<li>
										<a href="${ctx}/product/findByPid.do?id=${product.id}" target="_blank"><img src="${product.image}" alt="${product.name}" style="display: block;"></a>
									</li>
						 </c:forEach>	
						</ul>
			</div>
		</div>
	<div class="panel-foot text-center" style="margin: 0 auto; width: 350px" >
      <pg:pager  url="${ctx}/login/index.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
			<pg:param name="role" value="1"/>
			<pg:param name="name" value="${product.name}"/>
		<pg:last>  
			共${pagers.total}记录,共${pageNumber}页,  
		</pg:last>  
			当前第${curPage}页 
  
        <pg:first>  
    		<a href="${pageUrl}">首页</a>  
		</pg:first>  
		<pg:prev>  
    		<a href="${pageUrl}">上一页</a>  
		</pg:prev>  
      
       	<pg:pages>  
        	<c:choose>  
            	<c:when test="${curPage eq pageNumber}">  
                	<font color="red">[${pageNumber }]</font>  
            	</c:when>  
            	<c:otherwise>  
               		<a href="${pageUrl}">${pageNumber}</a>  
            	</c:otherwise>  
        	</c:choose>  
    	</pg:pages>
             
        <pg:next>  
    		<a href="${pageUrl}">下一页</a>  
		</pg:next>  
		<pg:last>  
			<c:choose>  
            	<c:when test="${curPage eq pageNumber}">  
                	<font color="red">尾页</font>  
            	</c:when>  
            	<c:otherwise>  
               		<a href="${pageUrl}">尾页</a>  
            	</c:otherwise>  
        	</c:choose> 
    		  
		</pg:last>
	</pg:pager>
    </div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南</dt>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
<div class="container footer">
<!-- 	<div class="span24"> -->
<!-- 		<div class="footerAd"> -->
<%-- 					<img src="${ctx}/resource/common/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"> --%>
<!-- </div>	</div> -->
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
</body>
</html>