<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>交大邦应用管理后台</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/login.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="js/base.js"></script>
    <script src="js/web/login.js"></script>
</head>
<body>
<img src="img/bg.jpg" class="loginBg">

<div class="login">
    <!-- 登陆背景logo -->
    <div class="login-title"></div>
    <!-- 登陆页面主体 -->
    <div class="login-menu">
        <h2>用户登录</h2>

        <form id="admin-login" action="<%=basepath%>/pc/login/dologin">
            <ul>
                <li>
                    <span>用户名：</span>
                    <input id="username" name="username" type="text" class="inputText">
                </li>
                <li>
                    <span>密码：</span>
                    <input id="password" name="password" type="password" class="inputText">
                </li>
                <li>
                    <span>验证码：</span>
                    <input id="verifycode" name="verifycode" type="text" class="inputCode">
                    <i><img id="verifycodeImg" src="<%=basepath%>/login/verifycode"></i>
                </li>
            </ul>
            <div class="ui-btn">
                <input id="rmbUser" name="rmbUser" type="checkbox" class="uiBtn">
                <b>记住登陆状态</b>
                <input id="inputSub" type="submit" class="inputSub" value="登陆">
            </div>
        </form>
    </div>
</div>
</body>
</html>
