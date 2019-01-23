<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 用户信息</title>
    <jsp:include page="../include/header.jsp"></jsp:include>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../include/nav.jsp"></jsp:include>
    <jsp:include page="../include/aside.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                控制面板
                <small>用户信息</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/myshop/main"><i class="fa fa-users"></i> 控制面板</a></li>
                <li class="active">用户信息</li>
            </ol>
        </section>

        <section class="content">
            <c:if test="${result.status == 200}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${result.message}
                </div>
            </c:if>
        <div class="row">
            <div class="col-xs-12">
                <div class="box no-border">
                    <div class="box-header">
                        <h3 class="box-title">用户信息</h3>

                        <div class="row" style="padding-left: 14px;padding-top: 10px;">
                            <a href="/myshop/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 添加</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i> 删除</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>
                        </div>
                        <div class="row" style="padding-right: 100px;padding-top: 10px">
                            <form:form cssClass="form-horizontal" action="/myshop/search" method="post" modelAttribute="tbUser">
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-3  control-label">用户名</label>

                                        <div class="col-sm-9">
                                            <form:input cssClass="form-control" placeholder="用户名"  path="username" />
                                        </div>
                                    </div>
                                 </div>
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-3  control-label">邮箱</label>

                                        <div class="col-sm-9">
                                            <form:input cssClass="form-control" placeholder="邮箱"  path="email" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-3  control-label">手机</label>

                                        <div class="col-sm-9">
                                            <form:input cssClass="form-control" placeholder="手机"  path="phone" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <button type="submit" class="btn btn-info"><i class="fa fa-search"></i> 搜索</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>用户名</th>
                                <th>电话</th>
                                <th>邮箱</th>
                                <th>创建日期</th>
                                <th>修改日期</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items = "${tbUserInfo}" var="user" step="1">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.email}</td>
                                    <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-DD HH:mm:ss"></fmt:formatDate></td>
                                    <td><fmt:formatDate value="${user.updated}" pattern="yyyy-MM-DD HH:mm:ss"></fmt:formatDate></td>
                                    <td>
                                        <a href="#" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> 修改</a>&nbsp;&nbsp;&nbsp;
                                        <a href="#" type="button" class="btn btn-danger btn-sm"><i class="fa  fa-trash-o"></i> 删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
        </section>
    </div>
    <jsp:include page="../include/copyright.jsp"></jsp:include>
</div>

<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>