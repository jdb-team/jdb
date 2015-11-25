<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>活动管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/index.css"/>
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
    <a href="">活动管理</a>
</div>

<div class="yx-wrapper">
    <jsp:include page="left.jsp" />
    <aside class="right-side">
        <section class="content">
                <div class="admin-main">
                    <div class="admin-bd">
                        <div class="user-box home1">
                            <div class="administrator clearfix">
                                <div class="group-item">
                                    <label class="gx-texttitle">
                                        ID：
                                    </label>
                                    <input id="qid" name="id" type="text" class="gx-textbox" placeholder="请输入活动编号">
                                    <label class="gx-texttitle">
                                        活动名称：
                                    </label>
                                    <input id="qtitle" name="title" type="text" class="gx-textbox" placeholder="请输入活动名称">
                                </div>
                                <div class="group-item">
                                    <label class="gx-texttitle">
                                        创建时间：
                                    </label>
                                    <input id="qstartTime" name="startTime" type="text" class="gx-textbox form_datetime" placeholder="请选择开始时间" size="16" readonly>
                                    <span>
                                       到
                                    </span>
                                    <input id="qendTime" name="endTime" type="text" class="gx-textbox form_datetime" placeholder="请选择结束时间" size="16" readonly>
                                </div>
                                <div class="group-item">
                                    <label class="gx-texttitle1">
                                        活动创建账号：
                                    </label>
                                    <input id="qusername" type="text" class="gx-textbox" placeholder="请输入账号">
                                    <label class="gx-texttitle1">
                                        活动创建者：
                                    </label>
                                    <input id="qrealName" type="text" class="gx-textbox" placeholder="请输入创建者">
                                </div>
                                <button id="query" class="gx-button gx-button-info gx-button-small admin-btn fr">查询</button>
                            </div>
                            <div class="admin-table">
                                <table id="datatable" class="tab">
                                    <tr class="tab-h">
                                        <td>序号</td>
                                        <td>ID</td>
                                        <td>活动名称</td>
                                        <td>
                                            <select id="stateslt" class="gx-textbox">
                                                <option>活动状态</option>
                                                <option>正常</option>
                                                <option>冻结</option>
                                            </select>
                                        </td>
                                        <td>活动创建账号</td>
                                        <td>活动创建者</td>
                                        <td>活动创建时间</td>
                                        <td>操作</td>
                                    </tr>
                                </table>
                                <jsp:include page="pager.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </aside>
</div>

<script src="js/admin_yc.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="js/page.js"></script>
<script src="js/web/activity_manage.js"></script>

<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language:  'zh-CN'
    });

</script>
<jsp:include page="footer.jsp" />