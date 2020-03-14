<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>超市</title>
<link href="${ctx}/resource/common/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resource/common/css/product.css" rel="stylesheet" type="text/css"/>

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
</div>	</div>
	
<%@ include file="/common/menu.jsp" %>
	
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<c:forEach items="${categoryList}" var="cs1" >
						            <dl>
						                  <dt>
						                  <a href="${ctx}/product/findByCid.do?cid=${cs1.id}"/>${cs1.cname}</a> 
						                  </dt>
									      <dt>
											<c:forEach items="${categorySecList}" var="cs" >
										          <c:if test="${cs.categoryId == cs1.id}">
										           <dd>
														<a href="${ctx}/product/findByScid.do?scid=${cs.id}"/>${cs.scname}</a> 
													</dd>
										          </c:if>
									         </c:forEach>
									     </dt>
						         </dl>
			</c:forEach>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${ctx}/resource/common/image/蔬菜 - Powered By Mango Team.htm" method="get">
					
				<div id="result" class="result table clearfix">
						<ul>
							<c:forEach items="${pagers.datas}" var="product" >
								<li>
										<a href="${ctx}/product/findByPid.do?id=${product.id }"/>
											<img src="${product.image}" width="170" height="170"  style="display: inline-block;">
											   
											<span style='color:green'>
                                               ${product.name}
											</span>
											 
											<span class="price">
												柜台价： ￥${product.price }
											</span>
											 
										</a>
								</li>
							</c:forEach>	
						</ul>
				</div>

	<div class="panel-foot text-center" style="margin: 0 auto; width: 350px" >
	<c:if test="${cid!=null}">
      <pg:pager  url="${ctx}/product/findByCid.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="10" export="curPage=pageNumber" >
		<pg:param name="cid"  value="${cid}"/>
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
	</c:if>
	<c:if test="${scid!=null}">
      <pg:pager  url="${ctx}/product/findByScid.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="10" export="curPage=pageNumber" >
                <pg:param name="scid"  value="${scid}"/>
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
	</c:if>
	
	
    </div>
			</form>
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
		<div class="copyright">Copyright©2017-2017 超市   版权所有</div>
	</div>
</div>
</body></html>