<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版 | Log in</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="../plugins/iCheck/square/blue.css">
</head>

<script>
    window.onload = function () {
        if (window.parent.window != window) {
            window.top.location = "/login.jsp";
        }
    }
</script>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="all-admin-index.html">SaaS外贸出口云平台</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body" id="weixin">
        <p class="login-box-msg">登录系统</p>
        <form action="/login.do" method="post">
            <div class="form-group has-feedback">
                <input type="email" name="email" class="form-control" placeholder="Email">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label style="color: red">${error}</label>
                    </div>
                </div>
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
        </form>
        <div class="social-auth-links text-center" >
            <p>- 或者 -</p>
            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-qq"></i> 腾讯QQ用户登录</a>
            <a onclick="mounted()" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-weixin"></i> 微信用户登录</a>
        </div>

    </div>
</div>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script>
    function mounted(){
        var obj = new WxLogin({
            id: "weixin",
            appid: "wx3bdb1192c22883f3",
            scope: "snsapi_login",
            redirect_uri: "http://note.java.itcast.cn/weixinlogin.do"
        });
    }
</script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
