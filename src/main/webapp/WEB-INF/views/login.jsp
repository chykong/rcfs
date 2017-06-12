<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${webTitle}</title>
    <%@ include file="common/header.jsp" %>
    <script src="${staticServer }/assets/js/userBrower.js"></script>
    <script src="${staticServer }/assets/js/jCookie.js"></script>
</head>
<style>
  .login-back{
        background: url('${staticServer}${loginBack}') round;
  }
  .login-container {
      width: 300px;
      margin-top: 4%;
      position: absolute;
      right: 0;
  }
  .login-layout .widget-box{
      border: none;
      border-radius: 0;
      background: none;
      padding-top: 0;
      margin-top: 0;
  }
  .widget-body {
       background: none;
  }
  .login-layout .widget-box .widget-main{
      background: rgba(0,0,0,0.5);
  }
  .btn-login{
      border: #00AAEE!important;
      background: #00AAEE!important;
      border-radius: 5px!important;
  }
  .btn-login:hover{
      border: #00AAEE!important;
      background: #00AAEE!important;
      border-radius: 5px!important;
  }
</style>
<body class="login-layout login-back">
<div class="main-container login-main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger center" style="margin-top: 0">
                                         <b class="white">登录</b>
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="loginForm" action="checkLogin.htm" method="post">
                                        <fieldset>
                                            <label class="block clearfix"> <span
                                                    class="block input-icon input-icon-right">
                                                <input type="text" class="form-control" name="username" id="username"
                                                       placeholder="用户名"/>
                                                <i class="ace-icon fa fa-user"></i>
												</span>
                                            </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right"> <input type="password"
                                                                                                  class="form-control"
                                                                                                  name="password"
                                                                                                  id="password"
                                                                                                  placeholder="密码"/> <i
                                                class="ace-icon fa fa-lock"></i>
												</span>
                                        </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right"> <span id="lblMessage"
                                                                                                 class="errMsg"
                                                                                                 style="display: none"> 账号或密码输入错误！ </span>
												</span>
                                            <div class="space"></div>
                                            <div class="clearfix">
                                                <%--<label class="inline"> <input type="checkbox" class="ace" id="chk"/>--%>
                                                    <%--<span class="lbl"> 记住我</span>--%>
                                                <%--</label>--%>
                                                <button type="submit"
                                                        class="col-xs-12 btn btn-login btn-primary">
                                                     <span class="bigger-110">登录</span>
                                                </button>
                                            </div>
                                            <div class="space-4"></div>
                                            <br/> <label class=" clearfix"> <span
                                                    class="block input-icon ">
															<span class="inline input-icon input-icon-right" style="color: white"><a
                                                                    href="http://www.balance-soft.com" target="_blank" style="color: #00AAEE">北京百乐思技术有限公司</a> © 2017 提供技术支持
                                                            </span>
														</span></label>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="common/js.jsp" %>

<!-- inline scripts related to this page -->
<script type="text/javascript">
		jQuery(function($) {

			$("#loginForm").validate({
				errorElement : "label",
				errorClass : "valiError",
				errorPlacement : function(error, element) {
				},
				rules : {
					username : {
						required : true
					},
					password : {
						required : true
					}
				},
				messages : {
					username : {
						required : ""
					},
					password : {
						required : ""
					}
				},
				submitHandler : function(form) {
					checkLogin();
				}
			});

			var cookie_chk = jQuery.jCookie('bls_chk');
			if (cookie_chk != '' && cookie_chk != null && cookie_chk == '1') {
				$('#chk').prop("checked", true);
				$('#username').val(jQuery.jCookie('bls_username'));
			}
		});

		function checkLogin() {
			if ($('#chk').is(':checked')) {
				jQuery.jCookie('bls_chk', '1', 30);
				jQuery.jCookie('bls_username', $('#username').val(), 30);
			} else {
				jQuery.jCookie('bls_chk', '0', 30);
			}

			var username = $("#username").val();
			var password = $("#password").val();
			$.ajax({
				type : "post",
				url : "${dynamicServer}/checkLogin.htm",
				data : {
					username : username,
					password : password,
					terminal : getUserTerminalType(),
					explorerType : getExplorerInfo().browser,
					explorerVersion : getExplorerInfo().version
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$("#lblMessage").hide();
						window.location.href = "${dynamicServer}/index.htm";
					} else {
						$("#lblMessage").html(data.msgText);
						$("#lblMessage").show();
					}
				},
				error : function(data) {
					$("#lblMessage").html('登录失败');
					$("#lblMessage").show();
				}
			});
		}













</script>
</body>
</html>