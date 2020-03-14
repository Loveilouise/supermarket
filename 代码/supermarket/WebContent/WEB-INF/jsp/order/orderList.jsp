<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>订单页面</title>
<link href="${ctx}/resource/common/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/common/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/resource/common/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="./网上商城/index.htm"> 
					<img src="${ctx}/resource/common/image/r___________renleipic_01/61.jpg"  style="width: 189px; height: 50px;"alt="KTV"/></a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
<%-- 				<img src="${ctx}/resource/common/image/header.jpg" --%>
<!-- 					width="320" height="50" alt="正品保障" title="正品保障" /> -->
			</div>
		</div>

	 <%@ include file="/common/menu.jsp" %>

	</div>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li style="margin-left: 170px;color: red;font-size: 26px;">我的订单</li>
				</ul>
			</div>


			<table>
				<tbody>
				 <c:forEach items="${pagers.datas}" var="order" varStatus="l">
						<tr>
							<th colspan="5">订单编号:${order.uid}"&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
								color="red">￥${order.total}
							</font>
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
							       <c:if test="${order.status==0 }">
<%-- 									<a href="${ctx}/order_payOrder.do?id=${order.id}">付款</a> --%>
                                     <a href="${ctx}/order/saveOrder1.do?id=${order.id}">付款</a> 
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/order/cancelOrder.do?id=${order.id}">取消订单</a> 
									</c:if>
								  <c:if test="${order.status==1 }">
									已付款
					              </c:if>
								  <c:if test="${order.status==2 }">
								      	交易成功
<!-- 								  	&nbsp;&nbsp;&nbsp;&nbsp; -->
<%-- 									<a href="${ctx}/order_confirmStatus.do?id=${order.id}">确认收货</a> --%>
								</c:if>
							    <c:if test="${order.status==3 }">
									订单已取消
								</c:if>
<%-- 								 <c:if test="${order.status==4 }"> --%>
<!-- 									订单已取消 -->
<%-- 		  						</c:if> --%>
							</font>
							      &nbsp;&nbsp;  订单时间：<font color="red"><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>	
						
                            <c:forEach items="${orderItemList.datas}" var="orderItem" varStatus="l">
                            <c:if test="${orderItem.orderId==order.id }">
							<tr>
								<td width="60"><img
									src="${orderItem.product.image }"/>
								</td>
								<td>${orderItem.product.name }</td>
								<td>${orderItem.product.price }</td>
								<td class="quantity" width="60">
										${orderItem.count}</td>
								<td width="140"><span class="subtotal">￥
											${orderItem.subtotal }
								</span></td>
							</tr>
							 </c:if>
							</c:forEach>
                   </c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="panel-foot text-center" style="margin: 0 auto; width: 350px" >
      <pg:pager  url="${ctx}/order/findByUserId.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
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
	
	<div class="container footer">
<!-- 		<div class="span24"> -->
<!-- 			<div class="footerAd"> -->
<%-- 				<img src="${ctx}/resource/common/image\r___________renleipic_01/footer.jpg" alt="我们的优势" --%>
<!-- 					title="我们的优势" height="52" width="950"> -->
<!-- 			</div> -->
<!-- 		</div> -->
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