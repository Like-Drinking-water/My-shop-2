var app = function () {
    //iCheck
    var checkbox_master
    var checkbox

    //deleteMulti
    var deleteArray = new Array()
    var afterDelete = false

    var handleInit = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })

        checkbox_master = $("input[type='checkbox'].minimal.icheck_master")
        checkbox = $("tbody input[type='checkbox'].minimal")

        $("#delete_confirm").click(function () {
            if (afterDelete) {
                window.location.reload()
            }
            $("#modal-default").modal("hide")
            //如果选择删除的用户的数量为0
            if (deleteArray.length != 0) {
                setTimeout(function () {
                    $.ajax({
                        "url": "/myshop/deleteMulti",
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

        })

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

    var handleDeleteMulti = function () {
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

    return {
        init: function() {
            handleInit()
        },
        check: function() {
            handleCheck()
        },
        getCheckBox: function() {
            return checkbox
        },
        deleteMulti: function () {
            handleDeleteMulti()
        }
    }

} ()

$(document).ready(function () {
    app.init()
    app.check()
})