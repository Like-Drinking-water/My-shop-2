var app = function () {
    var checkbox_master
    var checkbox

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

    return {
        init: function() {
            handleInit()
        },
        check: function() {
            handleCheck()
        },
        getCheckBox: function() {
            return checkbox
        }
    }

} ()

$(document).ready(function () {
    app.init()
    app.check()
})