<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<!-- Head Begin -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的商城 | 登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="static/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="static/assets/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="static/assets/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="static/assets/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="static/assets/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<!-- ./Head End -->
<!-- Body Begin -->
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <b>我的商城</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>

        <form action="../myshop/login" method="post">
            <div class="form-group has-feedback">
                <input name="email" type="email" class="form-control" placeholder="邮箱" value=${email != null ? email : ""}>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码" value=${password != null ? password : ""}>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="alert alert-danger alert-dismissible" ${message == null ? "style='display: none'" : ""}>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h5><i class="icon fa fa-ban"></i> ${message}</h5>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <div class="checkbox icheck">
                        <label  class="remember" >
                            <input type="checkbox" id="remember" name="remember" value="1" ${remember != null ? "checked" : ""}/> 记住密码
                        </label>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="checkbox icheck">
                        <label class="auto-login">
                            <input type="checkbox" id="auto-login" name="auto-login" value="1"/> 自动登录
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>

        </form>
    </div>
</div>


<!-- jQuery 3 -->
<script src="static/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="static/assets/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        var i = 1;

        $(".auto-login").click(


            function ( ) {

                if (i == 1) {

                    $(".remember div").addClass("checked")
                    $(".auto-login div").addClass("checked")
                    $(".auto-login div").attr("aria-checked","true")
                    $(".remember div").attr("aria-checked","true")
                    $("#remember").attr("checked", "checked")
                    $("#auto-login").attr("checked", "checked")
                    i = i - 1;


                }
                else {

                    $(".auto-login div").removeClass("checked")
                    $(".remember div").removeClass("checked")
                    $(".auto-login div").attr("aria-checked","false")
                    $(".remember div").attr("aria-checked","false")
                    $("#remember").removeAttr("checked")
                    $("#auto-login").removeAttr("checked")
                    i = i + 1;
                }
            }
        );

        $("#auto-login").on("ifChecked",
            function() {
                $(".remember div").addClass("checked")
                $(".auto-login div").addClass("checked")
                $(".auto-login div").attr("aria-checked", "true")
                $(".remember div").attr("aria-checked", "true")
                $("#remember").attr("checked", "checked")
                $("#auto-login").attr("checked", "checked")
            }
        )

        $("#auto-login").on("ifUnchecked",
            function () {
                $(".remember div").removeClass("checked")
                $(".auto-login div").removeClass("checked")
                $(".auto-login div").attr("aria-checked", "true")
                $(".remember div").attr("aria-checked", "true")
                $("#remember").removeAttr("checked")
                $("#auto-login").removeAttr("checked")
            }
        )

        $("#remember").on("ifToggled",
            function (event) {
                $("#remember").iCheck("toggled");
                a();
                function a() {
                    if ($("#remember").attr("checked") == undefined) {
                        $("#remember").attr("checked", "checked")
                    } else {
                        $("#remember").removeAttr("checked")
                    }

                }
            },


        )



        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });




    });
</script>
</body>
<!-- ./Body End -->
</html>
