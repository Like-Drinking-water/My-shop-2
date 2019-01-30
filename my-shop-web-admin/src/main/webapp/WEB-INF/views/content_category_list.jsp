<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 内容管理</title>
    <jsp:include page="../include/header.jsp"></jsp:include>
    <link rel="stylesheet" href="/myshop/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css">
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
                <small>内容分类</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/myshop/main"><i class="fa fa-users"></i> 控制面板</a></li>
                <li class="active">内容分类</li>
            </ol>
        </section>

        <div class="content">
            <c:if test="${result.status == 200}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${result.message}
                </div>
            </c:if>
            <div class="row">
                <div class="col-xs-12" >
                    <div class="box no-border">
                        <div class="box-header">
                            <h3 class="box-title">内容分类</h3>

                            <div class="row" style="padding-left: 14px;padding-top: 10px;">
                                <a href="/myshop/content/category/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 添加</a>&nbsp;&nbsp;&nbsp;
                                <button id="delete_multi" type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>
                            </div>

                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <table id="treeTable" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>排序</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${contentCategory}" var="content">
                                        <tr id="${content.id}" pId="${content.parentId}">
                                            <td>${content.id}</td>
                                            <td><span controller="true">${content.name}</span></td>
                                            <td>${content.sortOrder}</td>
                                            <td>
                                                <a href='#' type='button' class='btn btn-primary btn-sm'><i class='fa fa-edit'></i> 修改</a>&nbsp;&nbsp;&nbsp;
                                                <button type='button' class='btn btn-danger btn-sm'><i class='fa  fa-trash-o'></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                                <button href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 添加下级菜单</button>&nbsp;&nbsp;&nbsp;
                                                <button href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 添加同级菜单</button>&nbsp;&nbsp;&nbsp;
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
            </div>


            </section>
        </div>

    </div>
    <jsp:include page="../include/copyright.jsp"></jsp:include>
    <jsp:include page="../include/footer.jsp"></jsp:include>
    <script src="/myshop/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
    <sys:modal ></sys:modal>
<script>
    $('#treeTable').treeTable({
        column: 1
    });
</script>
</body>
</html>
