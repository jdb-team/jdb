<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<script src="js/base.js"></script>
<script src="js/jquery.min.js"></script>
<header>
    <div class="header clearfix">
        <h1 class="yx-logo">
            <a href="index.jsp">交大邦应用管理后台<i>1.0 系统</i></a>
        </h1>
        <a href="javascript:;" class="sidebar-toggle">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="login-user">
            <div class="top-infor">
                <span>
                    <i class="fa fa-question-circle"></i>
                    <a href="">帮助</a>
                </span>
                <span>
                    <i class="fa fa-sign-in"></i>
                    <a href="javascript:window.location='<%=basepath%>/pc/login/loginout';">退出</a>
                </span>
            </div>
            <div class="top-infor">
                <span>
                    <i class="fa fa-user"></i>
                    管理员：<a href="###"><%=request.getSession().getAttribute("realName")%></a>
                </span>
                <span>
                    <i class="fa fa-wrench"></i>
                    <a href="#" id="editPass">修改密码</a>
                </span>
            </div>
        </div>
    </div>
</header>

<!-- 修改密码 -->
<div class="popUpBox edit-pass">
    <h1 class="popUpBoxTit">
        修改密码
        <a class="popUpBoxNo"  href="javascript:void(0)"></a>
    </h1>
    <div class="popUpBoxCon">
        <div class="newPop-menu">
            <ul>
                <li>
                    <label><span>*</span>账&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp户：</label>
                    <b><span id="username"></span></b>
                </li>
                <li>
                    <label><span>*</span>密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：</label>
                    <input id="password" name="password" type="password" class="newpop-tet"/>
                </li>
                <li>
                    <label><span>*</span>再次输入：</label>
                    <input id="password1" name="password1" type="password" class="newpop-tet"/>
                </li>
            </ul>
            <div class="addCancle">
                <button id="updateBtn" class="gx-button gx-button-info">修改</button>
                <button class="gx-button gx-button-error popUpBoxNo">取消</button>
            </div>
        </div>
    </div>
</div>