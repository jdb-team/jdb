<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>圈子管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Globak-x">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="css/admin-ui.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="css/jdb.css"/>
    <link rel="stylesheet" href="css/index.css"/>

</head>
<body>
<!-- header-->
<jsp:include page="header.jsp"/>
<!-- crumbs-->
<div class="gx-crumbs clearfix">

    <a class="gx-button gx-button-small gx-crumbs-btn" href="circle_create.jsp">创建圈子</a>

    <i class="fa fa-paperclip"></i>
    <span>当前位置：</span>
    <span>首页</span>
    <i class="fa fa-angle-right"></i>
    <a href="">圈子管理</a>
</div>

<div class="yx-wrapper clearfix">
    <jsp:include page="left.jsp"/>

    <aside class="right-side">
        <section class="content">
            <div class="admin-main">
                <div class="admin-hd">
                    <h3>圈子管理</h3>
                </div>
                <div class="admin-bd">
                    <div class="user-box">
                        <div class="administrator">
                            <div class="group-item">
                                <label class="gx-texttitle">
                                    圈子名称：
                                </label>
                                <input id="qtitle" type="text" class="gx-textbox" placeholder="请输入圈子标题">
                                <label class="gx-texttitle">
                                    创建人：
                                </label>
                                <input id="qrealname" type="text" class="gx-textbox" placeholder="创建人">
                            </div>
                            <div class="group-item">
                                <label class="gx-texttitle">
                                    创建日期：
                                </label>
                                <input id="qstartTime" type="text" class="gx-textbox form_datetime"
                                       placeholder="请选择开始时间" size="16"
                                       readonly>
                                    <span>
                                       到
                                    </span>
                                <input id="qendTime" type="text" class="gx-textbox form_datetime" placeholder="请选择结束时间"
                                       size="16"
                                       readonly>
                            </div>
                            <button id="query" class="gx-button gx-button-info gx-button-small admin-btn fr">查询</button>
                        </div>
                        <div class="user-list circle-list">
                            <table id="datatable" class="gx-table gx-table-striped circle-table">
                                <tbody>
                                <tr>
                                    <td>圈子号</td>
                                    <td>圈子图标</td>
                                    <td>圈子大图</td>
                                    <td>圈子名称</td>
                                    <td>
                                        <select id="stateslt" class="gx-textbox">
                                            <option selected>状态</option>
                                            <option>正常</option>
                                            <option>下线</option>
                                        </select>
                                    </td>
                                    <td>创建时间</td>
                                    <td>创建人</td>
                                    <td>关注人数</td>
                                    <td>操作</td>
                                </tr>
                                <tr class="gx-table-actived">
                                    <td>888888</td>
                                    <td><img class="circle-logo" src="img/300x300.png" alt=""/></td>
                                    <td><a class="links" href="">点击查看</a></td>
                                    <td>股市不争气</td>
                                    <td>叽里咕噜大家有啥说啥，有啥扯啥，文明用语。</td>
                                    <td class="gx-table-info">正常</td>
                                    <td>2013-04-23</td>
                                    <td>33333</td>
                                    <td>
                                        <a class="gx-button gx-button-info gx-button-small" href="###">查看信息</a>
                                        <a class="gx-button gx-button-warning gx-button-small"
                                           href="circle_edit.jsp">编辑</a>
                                        <a class="gx-button gx-button-error gx-button-small" href="###">下线</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <jsp:include page="pager.jsp"/>
                        </div>
                    </div>
                </div>
            </div>

        </section>
    </aside>
</div>
<div class="popUpBox circlepic">
    <h1 class="popUpBoxTit">
        圈子大图
        <a class="popUpBoxNo"  href="javascript:void(0)"></a>
    </h1>
    <div class="popUpBoxCon">
        <img width="920" height="517.5" id="circleImg" src=""/>
    </div>
</div>
<script src="js/admin_yc.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="js/page.js"></script>
<script src="js/web/circle_list.js"></script>

<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN'
    });

</script>
<jsp:include page="footer.jsp"/>