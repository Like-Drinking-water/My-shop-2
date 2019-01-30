<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
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
                <small>内容列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/myshop/main"><i class="fa fa-users"></i> 控制面板</a></li>
                <li class="active">内容列表</li>
            </ol>
        </section>

        <div class="content">
            <c:if test="${result.status == 200}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${result.message}
                </div>
            </c:if>
            <div id="search_box" class="row" style="display: none">
                <div class="col-xs-12">
                    <div class="box box-info form-horizontal">
                        <div class="box-header with-border">
                            <div class="box-header with-border">
                                <h3 class="box-title">高级搜索</h3>
                            </div>
                            <div class="row" style="padding-top: 10px">
                                <div class="col-xs-12 col-md-3">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-3  control-label">标题</label>

                                        <div class="col-sm-9">
                                            <input class="form-control" placeholder="标题"  id="title" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-3">
                                    <div class="form-group">
                                        <label for="sub_title" class="col-sm-3  control-label">子标题</label>

                                        <div class="col-sm-9">
                                            <input class="form-control" placeholder="子标题"  id="sub_title" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label for="title_desc" class="col-sm-3  control-label">标题描述</label>

                                        <div class="col-sm-9">
                                            <input class="form-control" placeholder="标题描述"  id="title_desc" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-2">
                                    <button type="button" class="btn btn-info" id="search_button"><i class="fa fa-search"></i> 搜索</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-xs-12" >
                    <div class="box no-border">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>

                            <div class="row" style="padding-left: 14px;padding-top: 10px;">
                                <a href="/myshop/content/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 添加</a>&nbsp;&nbsp;&nbsp;
                                <button id="delete_multi" type="button" class="btn btn-default btn-sm" onclick="app.deleteMulti('/myshop/content/deleteMulti')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-info btn-sm" onclick="$('#search_box').css('display') == 'none' ? $('#search_box').show('fast') : $('#search_box').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                            </div>

                            <!-- /.box-header -->
                            <div class="box-body table-responsive">
                                <table id="dataTable" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>
                                            <label>
                                                <input type="checkbox" class="minimal icheck_master">
                                            </label>
                                        </th>
                                        <th>ID</th>
                                        <th>标题</th>
                                        <th>子标题</th>
                                        <th>标题描述</th>
                                        <th>链接</th>
                                        <th>图片1</th>
                                        <th>图片2</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

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
    <sys:modal></sys:modal>
    <div class="modal fade" id="modal-detail">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">查看详情</h4>
                </div>
                <div class="modal-body">
                    <p id="modal-detail-body"></p>
                </div>
                <div class="modal-footer">
                    <button id="confirm" type="button" class="btn btn-primary" onclick="$('#modal-detail').modal('hide')">确认</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <script>
        var url = "/myshop/content/deleteMulti"

        $(function () {
            function date(data) {
                var date = new Date(data)
                var year = date.getFullYear()
                var month = date.getMonth() + 1
                var day = date.getDate();
                var hours = date.getHours();
                var minutes = date.getMinutes();
                var seconds = date.getSeconds();
                return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds
            }

            var columns = [
                {
                    data:function ( row, type, val, meta) {
                        return "<input id=" + row.id + " type='checkbox' class='minimal'>"
                    }
                },
                { data: 'id' },
                { data: 'title' },
                { data: 'subTitle' },
                { data: 'titleDesc' },
                {
                    data: function ( row, type, val, meta) {
                        if (row.url == null) {
                            return ""
                        }
                        else {
                            return "<a href='" + row.url + "' target='_blank'>查看</a>"
                        }
                    }
                },
                {
                    data: function ( row, type, val, meta) {
                        if (row.pic == null) {
                            return ""
                        }
                        else {
                            return "<a href='" + row.pic + "' target='_blank'>查看</a>"
                        }
                    }
                },
                {
                    data: function ( row, type, val, meta) {
                        if (row.pic2 == null) {
                            return ""
                        }
                        else {
                            return "<a href='" + row.pic2 + "' target='_blank'>查看</a>"
                        }
                    }
                },
                {
                    data: 'updated',
                    render: function ( data, type, row ) {
                        return date(data)
                    }
                },
                {
                    data:function ( row, type, val, meta) {
                        var urlDelete = "/myshop/content/deleteMulti"
                        var urlLook = "/myshop/content/detail?id=" + row.id
                        return "<button href=\'/myshop/content/detail?id=" + row.id + "\' type='button' class='btn btn-default btn-sm' onclick='app.showDetail(\""+ urlLook +"\")'><i class='fa fa-search'></i> 查看</button>&nbsp;&nbsp;&nbsp;"+
                            "<a href='/myshop/content/form?id=" + row.id + "' type='button' class='btn btn-primary btn-sm'><i class='fa fa-edit'></i> 修改</a>&nbsp;&nbsp;&nbsp;" +
                            '<button type="button" class="btn btn-sm btn-danger" onclick="app.deleteOne(\'' + row.id + '\', \'' + urlDelete + '\')"><i class="fa fa-trash-o"></i> 删除</button>'
                    }
                },
            ]

            var datatable = app.initPage("/myshop/content/page", columns)

            $("#search_button").click(function () {
                var title = $("#title").val();
                var sub_title = $("#sub_title").val();
                var title_desc = $("#title_desc").val();
                var param = {
                    "title": title,
                    "subTitle": sub_title,
                    "titleDesc": title_desc
                }

                datatable.settings()[0].ajax.data = param;
                datatable.ajax.reload();
            })

        })
    </script>
</body>
</html>