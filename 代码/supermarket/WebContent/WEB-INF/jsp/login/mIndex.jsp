<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="${ctx}/resource/web/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resource/web/js/jquery.js"></script>
 <link href="${ctx}/resource/web/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/stylesheet.css" rel="stylesheet">
    <link href="${ctx}/resource/web/css/index.css" rel="stylesheet">
    <link href="${ctx}/resource/web/icon/font-awesome.css" rel="stylesheet">
    <script src="${ctx}/resource/web/js/jquery-1.10.2.js"></script>
    <script src="${ctx}/resource/web/js/jquery-ui-1.10.3.js"></script>
    <script src="${ctx}/resource/web/js/bootstrap.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.collapsible.min.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.mCustomScrollbar.min.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.mousewheel.min.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.uniform.min.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.sparkline.min.js"></script>
    <script src="${ctx}/resource/web/js/library/chosen.jquery.min.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.easytabs.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/excanvas.min.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/jquery.flot.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/jquery.flot.pie.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/jquery.flot.selection.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/jquery.flot.resize.js"></script>
    <script src="${ctx}/resource/web/js/library/flot/jquery.flot.orderBars.js"></script>
    <script src="${ctx}/resource/web/js/library/maps/jquery.vmap.js"></script>
    <script src="${ctx}/resource/web/js/library/maps/maps/jquery.vmap.world.js"></script>
    <script src="${ctx}/resource/web/js/library/maps/data/jquery.vmap.sampledata.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.autosize-min.js"></script>
    <script src="${ctx}/resource/web/js/library/charCount.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.minicolors.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.tagsinput.js"></script>
    <script src="${ctx}/resource/web/js/library/fullcalendar.min.js"></script>
    <script src="${ctx}/resource/web/js/library/footable/footable.js"></script>
    <script src="${ctx}/resource/web/js/library/footable/data-generator.js"></script>
    <script src="${ctx}/resource/web/js/library/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/resource/web/js/library/bootstrap-timepicker.js"></script>
    <script src="${ctx}/resource/web/js/library/bootstrap-datepicker.js"></script>
    <script src="${ctx}/resource/web/js/library/bootstrap-fileupload.js"></script>
    <script src="${ctx}/resource/web/js/library/jquery.inputmask.bundle.js"></script>
    <script src="${ctx}/resource/web/js/flatpoint_core.js"></script>

    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.html">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.html">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.html">
      <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.html">
          <script src="${ctx}/resource/web/js/calendar.js"></script>
    <script src="${ctx}/resource/web/js/forms.js"></script>
    <script src="${ctx}/resource/web/js/dashboard.js"></script>
<script>
    function getclassname(obj){
		if(document.getElementsByClassName('link_onclick').length==0){
			obj.className='link_onclick';
			obj.id='link_onclick';
			}else{
				var obj1=document.getElementById('link_onclick');
				obj1.className='111';
				obj1.id='1';
				obj.className='link_onclick';
			   obj.id='link_onclick';
			}
		
		}
    </script>
    
    <script>
        jQuery('#vmap').vectorMap({
            map:"world_en",
            backgroundColor:null,
            color:"#ffffff",
            hoverOpacity:.7,
            selectedColor:"#2d91ef",
            enableZoom:0,
            showTooltip:1,
            values:sample_data,
            scaleColors:["#8cc3f6","#5c86ac"],
            normalizeFunction:"polynomial",
            onRegionClick:function(){alert("This Region has "+(Math.floor(Math.random()*10)+1)+" users!"
            )}
        });

        jQuery(document).ready(function($) {
            $('.footable').footable();
            $('.responsive_table_container').mCustomScrollbar({
                set_height: 400,
                advanced:{
                  updateOnContentResize: true,
                  updateOnBrowserResize: true
                }
            });

            $('.responsive_table_container_2').mCustomScrollbar({
                set_height: 520,
                advanced:{
                  updateOnContentResize: true,
                  updateOnBrowserResize: true
                }
            });
        });
    </script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登陆</title>
</head>
</html>
<body>
    <header class="dark_grey">
     <!-- Header start -->
       <div class="top">
     <div class="login">
   <a href="${ctx}/login/tuichu.do?role=2"> <img src="${ctx}/resource/web/image/login.png"  /></a>
    </div>
    <div class="username" style="color: black"> 
      你好：${adminName}
    </div>
    <div class="user" >
    <img src="${ctx}/resource/web/image/user.png" />
    </div>
   

</div>
       
    </header>

    <div id="main_navigation" class="dark_navigation"> <!-- Main navigation start -->
        <div class="inner_navigation" >

            <ul class="main" >
                <li ><a class="expand"  href="dashboard.html"><i class="icon-home"></i>&nbsp;&nbsp;系统管理</a>
                    <ul class="sub_main" style="background-color: #20477C;">
                          <div style=" height:8px; width:100%;"></div>
                             <li><a href="${ctx}/user/findByObj.do" target="right" onClick="getclassname(this)">用户管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="float:right;font-size:20px;">></font></a></li>
                             <c:if test="${adminId==1}">
                               <li><a href="${ctx}/admin/findByObj.do" target="right" onClick="getclassname(this)">管理员管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="float:right;font-size:20px;">></font></a></li>
                             </c:if>
                           
                           <div style=" height:8px; width:100%;"></div>
                    </ul>
                </li>
                <li><a href="${ctx}/product/findByMap.do" target="right" onClick="getclassname(this)"><i class="icon-calendar"></i>&nbsp;&nbsp;商品管理</a></li>
                   
                     <li ><a class="expand"  href="dashboard.html"><i class="icon-table"></i>&nbsp;&nbsp;商品分类管理</a>
                    <ul class="sub_main" style="background-color: #20477C;">
                          <div style=" height:8px; width:100%;"></div>
                          <li><a href="${ctx}/category/findByMap.do" target="right" onClick="getclassname(this)">一级分类管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="float:right;font-size:20px;">></font></a></li>
                          <li><a href="${ctx}/categorySec/findByMap.do" target="right" onClick="getclassname(this)">二级分类管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="float:right;font-size:20px;">></font></a></li>
                           <div style="height:8px; width:100%;"></div>
                    </ul>
                </li>
                  <li><a href="${ctx}/order/findByMap.do" target="right" onClick="getclassname(this)"><i class="icon-tasks"></i>&nbsp;&nbsp;订单管理</a></li>
            </ul>
        </div>
    </div>

    <div id="content" class="no-sidebar">
     <!-- Content start -->
 
          <iframe  class="inner_navigation1" name="right" src="${ctx}/main.jsp" frameborder="0" width="100%" scrolling="auto" height="100%"></iframe> 

               <!-- Content END --> 
            </div>
            
        </div>
    </div>


    
  </body>





