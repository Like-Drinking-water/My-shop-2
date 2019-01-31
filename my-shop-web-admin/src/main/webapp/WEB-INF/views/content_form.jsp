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
    <link rel="stylesheet" href="/myshop/static/assets/plugins/dropzone/dropzone.css">
    <link rel="stylesheet" href="/myshop/static/assets/plugins/dropzone/min/basic.min.css" />
    <link rel="stylesheet" href="/myshop/static/assets/plugins/wangEditor/wangEditor.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background: #222D32">
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
                    <h3 class="box-title">${content.id == null? "添加" : "编辑"}内容</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->

                <from:form id="inputForm" cssClass="form-horizontal" action="/myshop/content/save" method="post" modelAttribute="content">
                    <from:hidden path="id"></from:hidden>
                    <div class="box-body">

                        <div class="form-group">
                            <label for="categoryName" class="col-sm-2 control-label">父目录</label>

                            <div class="col-sm-10">
                                <from:hidden path="categoryId" />
                                <input class="form-control required " placeholder="请选择" id="categoryName" readonly="true" data-toggle="modal" data-target="#modal-default"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">标题</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" placeholder="请输入标题"  path="title" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" placeholder="请输入子标题"  path="subTitle" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" placeholder="请输入标题描述"  path="titleDesc" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">链接</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control" placeholder="请输入链接地址"  path="url" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic" class="col-sm-2 control-label">图片1</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control" placeholder="请输入图片地址"  path="pic" />
                                <div id="drop_pic" class="dropzone"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic2" class="col-sm-2 control-label">图片2</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control" placeholder="请输入图片地址"  path="pic2" />
                                <div id="drop_pic2" class="dropzone"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">内容</label>

                            <div class="col-sm-10">
                                <form:hidden  path="content" />
                                <div id="editor">
                                    ${content.content}
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button id="btn_submit" type="submit" class="btn btn-info pull-right">提交</button>
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
    <script src="/myshop/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
    <script src="/myshop/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="/myshop/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
    <sys:modal title="请选择" message="<ul id='my-tree' class='ztree'></ul>"></sys:modal>
<script>
    Dropzone.autoDiscover = false;

    app.initDropzone({
        url: "/myshop/upload",
        id: "#drop_pic",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic").val(data.fileName)
            })
        }
    })

    app.initDropzone({
        url: "/myshop/upload",
        id: "#drop_pic2",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic2").val(data.fileName)
            })
        }
    })



    $(function () {
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.create()

        $("#btn_submit").click(
            function() {
                $("#content").val(editor.txt.html())
            }
        )

        var callback = function(nodes) {
            $("#categoryId").val(nodes[0].id)
            $("#categoryName").val(nodes[0].name)
            $("#modal-default").modal("hide")
        }
        app.initZTree("/myshop/content/category/tree/data", callback)


    })
</script>
</body>
</html>
