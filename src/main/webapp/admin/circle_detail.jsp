<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>圈子详情</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/admin_yc.js"></script>
</head>
<body>
<!-- header-->
<jsp:include page="header.jsp"/>
<!-- crumbs-->
<div class="gx-crumbs clearfix">
    <i class="fa fa-paperclip"></i>
    <span>当前位置：</span>
    <span>首页</span>
    <i class="fa fa-angle-right"></i>
    <a href="">圈子详情</a>
</div>

<div class="yx-wrapper clearfix">
    <jsp:include page="left.jsp"/>
    <aside class="right-side">
        <section class="content">
            <div class="admin-main">
                <div class="admin-hd">
                    <h3>圈子详情</h3>
                </div>
                <div class="admin-bd">
                    <div class="home">
                        <input type="hidden" id="id" value="<%=request.getParameter("id")%>"/>
                        <div id="circle-detail" class="activity notice fl">

                        </div>
                    </div>
                </div>
            </div>
        </section>
    </aside>
</div>
<script src="js/web/circle_detail.js"></script>
<jsp:include page="footer.jsp"/>