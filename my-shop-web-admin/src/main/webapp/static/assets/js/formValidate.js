var Validate = function () {

    var handlerInitValidate = function () {

        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
            return this.optional(element) || (length == 11 && mobile.test(value))
        }, "手机号码格式错误")

        $.validator.addMethod("email_exist", function (value, element) {
            var isExist = false;

            $.ajax({
                url: "/myshop/isEmailExist",
                type: "POST",
                async: false,
                dataType: "JSON",
                data: {"email":value},
                success: function (data) {

                    isExist = data.isExist

                }
            })
            return this.optional(element) || !isExist;

        }, "邮箱已存在")

        $("#inputForm").validate({
            errorElement: "span",
            errorClass: "help-block",
            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element)
            }
        })

    }

    return {
        init: function () {
            handlerInitValidate()
        }
    }
}();

$(document).ready(function () {
    Validate.init()
})