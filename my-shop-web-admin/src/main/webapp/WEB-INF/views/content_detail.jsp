<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/1/29
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <div class="box-body table-responsive">
        <table id="dataTable" class="table">
            <tbody>
            <tr>
                <th>ID</th>
                <td>${content.id}</td>
            </tr>
            <tr>
                <th>父目录</th>
                <td>${content.contentCategory.name}</td>
            </tr>
            <tr>
                <th>标题</th>
                <td>${content.title}</td>
            </tr>
            <tr>
                <th>子标题</th>
                <td>${content.subTitle}</td>
            </tr>
            <tr>
                <th>标题描述</th>
                <td>${content.titleDesc}</td>
            </tr>
            <tr>
                <th>链接</th>
                <td>${content.url}</td>
            </tr>
            <tr>
                <th>图片1</th>
                <td>${content.pic}</td>
            </tr>
            <tr>
                <th>图片2</th>
                <td>${content.pic2}</td>
            </tr>
            <tr>
                <th>内容</th>
                <td>${content.content}</td>
            </tr>
            <tr>
                <th>创建时间</th>
                <td><fmt:formatDate value="${content.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            <tr>
                <th>更新时间</th>
                <td><fmt:formatDate value="${content.updated}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            </tbody>
        </table>
    </div>
</head>
<body>

</body>
</html>
