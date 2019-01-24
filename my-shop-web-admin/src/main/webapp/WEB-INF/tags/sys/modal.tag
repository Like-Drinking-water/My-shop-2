<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="false" description="标题" %>
<%@ attribute name="message" required="true" description="提醒的信息" %>
<%@ attribute name="option" required="false" description="默认为提示 info为提示 confirm为确认" %>
<%@ attribute name="uri" required="false" description="要跳转的路径" %>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title == null ? "温馨提示" : title}</h4>
            </div>
            <div class="modal-body">
                <p>${message}&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button id="delete_confirm" type="button" class="btn btn-primary">确认</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
    $(function () {
        $("#delete_confirm").click(function () {
            <c:if test="${option == 'confirm'}">
                console.log(app.getCheckBox())
            </c:if>
            <c:if test="${option != 'confirm'}">
                $("#modal-default").modal("hide")
            </c:if>
        })

    })
</script>