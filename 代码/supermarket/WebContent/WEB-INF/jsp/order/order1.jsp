<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${ctx}/resource/common/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resource/common/css/cart.css" rel="stylesheet" type="text/css"/>

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

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li>生成订单成功</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<tr>
						<th colspan="5">订单编号:${order.uid }&nbsp;&nbsp;&nbsp;&nbsp;</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					    <c:forEach items="${orderItemList}" var="product" varStatus="l">
						<tr>
							<td width="60">
								<img src="${product.image}"/>
							</td>
							<td>
								${product.name }
							</td>
							<td>
								￥${product.price }
							</td>
							<td class="quantity" width="60">
								${product.count }
							</td>
							<td width="140">
								<span class="subtotal">￥${product.subtotal }</span>
							</td>
							
						</tr>
                               </c:forEach>
				</tbody>
			</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
					商品金额: <strong id="effectivePrice">￥${order.total}元</strong>
				</div>
			<form id="orderForm" action="${ctx}/order/payOrder.do" method="post">
				<input type="hidden" name="id" value="${order.id }"/>
				<div class="span24">
					<p>
							收货地址：<input name="address" type="text" value="${user.address }" style="width:350px"  readonly="readonly"/>
								<br />
							收货人&nbsp;&nbsp;&nbsp;：<input name="realName" type="text" value="${user.realName }" style="width:150px" readonly="readonly"/>
								<br /> 
							联系方式：<input name="phone" type="text"value="${user.phone}" style="width:150px" readonly="readonly"/>

						</p>
						<hr />
						<hr />
						<p style="text-align:right">
						  <a href="${ctx}/order/payOrder.do?id=${order.id}&type=1" style="color: red;font-size:20px" >积分购物券付款</a> 
                                     &nbsp;&nbsp;
                                      <a href="${ctx}/order/payOrder.do?id=${order.id}&type=2" style="color: red;font-size:20px">现金付款</a> 
									&nbsp;&nbsp; 
						</p>
				</div>
			</form>
		</div>
		
	</div>
<div class="container footer">
<!-- 	<div class="span24"> -->
<!-- 		<div class="footerAd"> -->
<%-- 					<img src="${ctx}/resource/common/image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950"> --%>
<!-- </div> -->
<!-- </div> -->
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