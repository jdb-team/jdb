<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>首页设置</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/index.css"/>
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
    <a href="">首页设置</a>
</div>

<div class="yx-wrapper clearfix">
    <jsp:include page="left.jsp"/>
    <aside class="right-side">
        <section class="content">
            <!-- 首页焦点图 -->
            <div class="admin-main">
                <div class="admin-bd">
                    <div class="home">
                        <div class="homeSetting">
                            <h2>首页焦点图</h2>

                            <form id="topicAndAtvsForm" name="topicAndAtvsForm" method="post"
                                  enctype="multipart/form-data">
                                <div id="topicAndAtvs" class="clearfix">
                                </div>
                            </form>
                            <div class="homeWord">
                                <div class="homeDetails fl">
                                    <p>注意：1、发布之前焦点数据可以还原；</p>

                                    <p class="homewordT">2、发布之后焦点数据正式上线；</p>
                                </div>
                                <div class="gx-submit-btn">
                                    <button id="rebackTopicAtvBtn" class="gx-button gx-button-success">还原</button>
                                    <button id="publishTopicAtvBtn" class="gx-button gx-button-error">发布</button>
                                </div>
                            </div>
                        </div>
                        <div class="homeSettingM">
                            <h2>朋友圈设置</h2>

                            <form id="circleForm" name="circleForm" method="post">
                                <div id="circleDiv">

                                </div>
                            </form>
                            <div class="clearfix"></div>
                            <div class="homeWord">
                                <div class="homeDetails fl">
                                    <p>注意：1、发布之前朋友圈设置可以还原；</p>

                                    <p class="homewordT">2、发布之后朋友圈设置正式上线；</p>
                                </div>
                                <div class="gx-submit-btn">
                                    <button id="rebackCircleBtn" class="gx-button gx-button-success">还原</button>
                                    <button id="publishCircleBtn" class="gx-button gx-button-error">发布</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end 职位发布 -->
        </section>
    </aside>
</div>
<script src="js/jquery.form.js"></script>
<script src="js/admin_yc.js"></script>
<script src="js/web/home_setting.js"></script>
<jsp:include page="footer.jsp"/>