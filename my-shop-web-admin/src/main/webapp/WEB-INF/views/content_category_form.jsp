<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../include/header.jsp"></jsp:include>
    <link rel="stylesheet" href="/myshop/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
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
                <small>内容管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/myshop/main"><i class="fa fa-users"></i> 控制面板</a></li>
                <li class="active">内容管理</li>
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
                    <h3 class="box-title">${content.id == null? "添加" : "编辑"}内容分类</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->

                <from:form id="inputForm" cssClass="form-horizontal" action="/myshop/content/category/save" method="post" modelAttribute="contentCategory">
                    <from:hidden path="id"></from:hidden>
                    <div class="box-body">

                        <div class="form-group">
                            <label for="categoryName" class="col-sm-2 control-label">父目录</label>

                            <div class="col-sm-10">
                                <from:hidden path="parent.id" />
                                <input class="form-control " placeholder="请选择" value="${contentCategory.parent.name}" id="categoryName" readonly="true" data-toggle="modal" data-target="#modal-default"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">分类名称</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" placeholder="请输入分类名称"  path="name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sortOrder" class="col-sm-2 control-label">分类排序</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required digits" placeholder="请输入分类排序"  path="sortOrder" />
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
    <script src="/myshop/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <sys:modal title="请选择" message="<ul id='my-tree' class='ztree'></ul>"></sys:modal>
<script>
    var callback = function(nodes) {
        $("#parentId").val(nodes[0].id)
        $("#categoryName").val(nodes[0].name)
        $("#modal-default").modal("hide")
    }
    app.initZTree("/myshop/content/category/tree/data", callback)
</script>
</body>
</html>
