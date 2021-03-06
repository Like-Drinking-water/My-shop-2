var app = function () {
    //iCheck
    var checkbox_master
    var checkbox

    //deleteMulti
    var deleteArray = new Array()
    var afterDelete = false

    //dropZone
    var defaultOption = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    }

    var handleInit = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })

        checkbox_master = $("input[type='checkbox'].minimal.icheck_master")
        checkbox = $("tbody input[type='checkbox'].minimal")

    }

    var handleCheck = function() {
        checkbox_master.on("ifClicked",
            function (e) {
                if (e.target.checked) {
                    checkbox.iCheck("uncheck")
                }

                else {
                    checkbox.iCheck("check")
                }
            }
        )
    }

    var handleDeleteMultiFunction = function (url) {
        if (afterDelete) {
            window.location.reload()
        }
        $("#modal-default").modal("hide")
        //如果选择删除的用户的数量为0
        if (deleteArray.length != 0) {
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "dataType": "JSON",
                    "data": {"ids":deleteArray.toString()},
                    "success": function (data) {

                        deleteArray = new Array()

                        $("#modal_message").html(data.message)
                        $("#modal-default").modal("show")

                        if (data.status == 200) {
                            afterDelete = true;
                        }

                    }
                })
            }, 500)

        }

    }

    var handleDeleteMulti = function (url) {
        $("#delete_confirm").click(function () {
            handleDeleteMultiFunction(url)
        })
        checkbox.each(function () {
            if ($(this).attr('id') != null && $(this).attr('id') != 'undefine' && $(this).is(':checked')) {
                deleteArray.push($(this).attr('id'))
            }
        })
        if (deleteArray.length == 0) {
            $("#modal_message").html("您至少选择一个要删除的对象")
        }

        else {
            $("#modal_message").html("您确定要执行删除操作吗")
        }
        $("#modal-default").modal("show")
    }

    var hanleDelete = function (id, url) {
        deleteArray.push(id)
        $("#delete_confirm").click(function () {
            handleDeleteMultiFunction(url)
        })
        $("#modal_message").html("您确定要执行删除操作吗")
        $("#modal-default").modal("show")
    }

    var handleInitPage = function (url, columns) {
        var datatable = $("#dataTable").DataTable({
            lengthChange:false,
            searching:false,
            serverSide:true,
            processing:true,
            deferRender:true,
            ordering:false,
            ajax: {
                url: url
            },
            columns: columns,
            drawCallback:function () {
                handleInit()
                handleCheck()
            },
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            }
        })
        return datatable
    }

    var handleShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data)
                $("#modal-detail").modal("show")
            }
        })
    }

    var handleInitZTree = function (url, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url:url,
                autoParam:["id"],
            }
        };
        $.fn.zTree.init($("#my-tree"), setting);

        $("#delete_confirm").click(function () {
            var zTree = $.fn.zTree.getZTreeObj("my-tree")
            var nodes = zTree.getSelectedNodes()
            if (nodes.length == 0) {
                alert("请先选择一个父目录")
            } else {
                callback(nodes)
            }
        })
    }

    var handleDropZone = function (option) {
        $.extend(defaultOption, option)

        new Dropzone(defaultOption.id, defaultOption)
    }

    return {
        init: function() {
            handleInit()
        },
        check: function() {
            handleCheck()
        },
        deleteMulti: function (url) {
            handleDeleteMulti(url)
        },
        initPage: function (url, columns) {
            return handleInitPage(url, columns)
        },
        deleteOne: function (id, url) {
            hanleDelete(id, url)
        },
        showDetail: function (url) {
            handleShowDetail(url)
        },
        initZTree: function (url, callback) {
            handleInitZTree(url, callback)
        },
        initDropzone: function (option) {
            handleDropZone(option)
        }
    }

} ()

$(document).ready(function () {
    app.init()
    app.check()
})