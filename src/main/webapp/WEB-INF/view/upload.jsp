<%--
  Created by IntelliJ IDEA.
  User: sima
  Date: 2017/1/12
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="/upload/singleupload" method="post" enctype="multipart/form-data">

    <input type="file" name="upload" >
    <button>提交</button>
</form>

<hr>
<form action="/upload/multiupload" method="post" enctype="multipart/form-data">

    <input type="file" name="upload" >
    <input type="file" name="upload" >
    <input type="file" name="upload" >
    <input type="file" name="upload" >
    <button>提交</button>
</form>
</body>
</html>
