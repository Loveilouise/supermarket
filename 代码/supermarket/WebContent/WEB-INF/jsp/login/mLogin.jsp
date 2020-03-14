<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="${ctx}/resource/css/login.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resource/web/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctx}/resource/web/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/resource/web/assets/fonts/fonts.googleapis.com.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/resource/web/assets/css/ace.min.css" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/resource/web/assets/css/ace-rtl.min.css" />
<style type="text/css">
	.login-layout{
	background-color: #319bcf;
	}

.login-container{
	margin-left:auto;
	margin-right:auto;
    top:20%;
	margin-top: 150px;

}
</style>
<script type="text/javascript" src="${ctx}/resource/web/js/jquery.js"></script>
<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/resource/web/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>
	<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/resource/web/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			});
		</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body class="login-layout" >
		<div class="main-container" >
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="white" id="id-text2">超市后台管理</span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请输入您的信息
											</h4>

											<div class="space-6"></div>

												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="adminName" id="adminName" type="text" class="form-control"value="" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="passWord" id="passWord" type="password" class="form-control" value="" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
<!-- 														<label class="inline"> -->
<!-- 															<input type="checkbox" class="ace" /> -->
<!-- 															<span class="lbl"> 记住</span> -->
<!-- 														</label> -->

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110" id="login"  onclick="check();">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>

						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		<div style="text-align:center;">
</div>
<script type="text/javascript">
			function check(){
				 var adminName= $("#adminName").val();
					var passWord = $("#passWord").val();
//                     var adminName= "nanjing";
// 					var passWord = "000";
					var params={adminName:adminName,passWord:passWord};
			 		$.ajax({
			            url: ctx+'/admin/index.json',
			 			type: 'POST',
			 			dataType: 'json',
			 		    data: params,
			 			async: false,
			 			success : function(data) {
			 			   if(data.result === 1){
// 								 alert("登录成功");
								 window.location.href='${ctx}/login/index.do?role=2';
								}else{
								     if(data.result == 2){
								    	 alert("密码输入错误");
								     }else{
								    	 if(data.result == 3){
								    		alert("该用户不存在"); 
								    	 }
								    	
								     }
								}
			 			},
			 				error : function() {
			 					alert("error");
			 				}
			 		});
			   }
	</script>
</body>
</html>