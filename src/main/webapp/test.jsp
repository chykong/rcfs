<%--
  Created by IntelliJ IDEA.
  User: chykong
  Date: 2017/6/22
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="assets/components/jquery.1x/dist/jquery.js"></script>
<script type="text/javascript">
    var basePath = "http://47.92.132.159:8080/rcfs/api/checkLogin";
    var token = "";
    //登录
    function login() {
        $.ajax({
            cache: false,
            type: "POST",
            url: basePath,
            headers: {'Content-Type': 'application/json'},
            data: {"username":"admin","password":"123456"},
            dataType: "json",
            timeout: 120000,
            error: function () {
            },
            success: function (data) {
                alert(data.data);
                token = data.data;
            }
        });
    }
    login();
</script>
</body>
</html>
