<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <jsp:include page="../include/nav.jsp"></jsp:include>
    <jsp:include page="../include/aside.jsp"></jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                控制面板
                <small>用户管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/myshop/main"><i class="fa fa-users"></i> 控制面板</a></li>
                <li class="active">用户管理</li>
            </ol>
        </section>
        <section class="content">
            <c:if test="${result.status == 500}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${result.message}
            </div>
            </c:if>
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">添加用户</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->

                <from:form id="inputForm" cssClass="form-horizontal" action="/myshop/save" method="post" modelAttribute="tbUser">
                    <div class="box-body">

                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required email" placeholder="请输入邮箱"  path="email" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <form:password cssClass="form-control required" placeholder="请输入密码" path="password" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" placeholder="请输入用户名" path="username" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">手机</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required mobile" placeholder="请输入手机号" path="phone" />
                            </div>
                        </div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button type="submit" class="btn btn-info pull-right">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </from:form>
            </div>
            <!-- /.box -->
            <!-- general form elements disabled -->
        </section>
    </div>
    <jsp:include page="../include/copyright.jsp"></jsp:include>
    <jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
