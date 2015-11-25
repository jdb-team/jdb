<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>创建圈子</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/jdb.css"/>
    <link rel="stylesheet" href="css/jquery.qeditor.css"/>
    <link rel="stylesheet" href="css/circle.css"/>
</head>
<body>
<!-- header-->
<jsp:include page="header.jsp" />
<!-- crumbs-->
<div class="gx-crumbs clearfix">

    <i class="fa fa-paperclip"></i>
    <span>当前位置：</span>
    <span>首页</span>
    <i class="fa fa-angle-right"></i>
    <a href="">圈子管理</a>
</div>

<div class="yx-wrapper clearfix">
    <jsp:include page="left.jsp" />

    <aside class="right-side">
        <section class="content">
                <div class="admin-main">
                    <div class="admin-hd">
                        <h3>创建圈子</h3>
                    </div>
                    <div class="admin-bd">
                        <!-- 创建圈子 -->
                        <div class="circle-create">
                            <form id="form1" name="form1" action="">
                                <div class="gx-textitem circle-create-item">
                                    <label class="gx-texttitle">
                                        圈子名字：
                                    </label>
                                    <input id="title" name="title" type="text" class="gx-textbox circle-create-name" placeholder="请输入名称..." min="0" max="14" required>
                                </div>
                                <div class="gx-textitem circle-create-item" style="width: 655px">
                                    <label class="gx-texttitle">
                                        圈子介绍：
                                    </label>
                                    <textarea id="introduce" class="intorduce" name="introduce" placeholder="请输入内容..." maxlength="80" required></textarea>
                                </div>
                                <div class="gx-textitem circle-create-item">
                                    <label class="gx-texttitle">
                                        圈子图片：
                                    </label>
                                    <div class="gx-file-img">
                                        <img id="picpathShow" src="img/400x200.png" width="400px" height="200px"/>
                                        <div class="gx-file-input">
                                            <input id="pic" name="pic" type="file">
                                            <a id="picpathBtn" href="javascript:void(0);" class="gx-button gx-button-success gx-file-input-btn">选择图片</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="gx-textitem circle-create-item">
                                    <label class="gx-texttitle">
                                        圈子图标：
                                    </label>
                                    <div class="gx-file-img">
                                        <img id="iconpathShow" src="img/400x200.png" width="80px" height="80px"/>
                                        <div class="gx-file-input">
                                            <input id="icon" name="icon" type="file">
                                            <a id="iconpathBtn" href="javascript:void(0);" class="gx-button gx-button-success gx-file-input-btn">选择图片</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="gx-submit-btn">
                                    <button id="submitBtn" class="gx-button gx-button-info">创建</button>
                                    <button id="cancelBtn" class="gx-button gx-button-error">取消</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </section>
    </aside>
</div>
<script src="js/jquery.uploadPreview.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/admin_yc.js"></script>
<script src="js/jquery.qeditor.js"></script>
<script src="js/web/circle_create.js"></script>
<jsp:include page="footer.jsp" />