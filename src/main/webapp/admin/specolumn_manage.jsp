<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<html>
<head lang="zh-CN">
    <meta charset="UTF-8">
    <title>专栏管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">`
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
    <a id="cSpeColumn" class="gx-button gx-button-small gx-crumbs-btn" href="###" >新建专栏</a>

    <i class="fa fa-paperclip"></i>
    <span>当前位置：</span>
    <span>首页</span>
    <i class="fa fa-angle-right"></i>
    <a href="">专栏管理</a>
</div>

<div class="yx-wrapper clearfix">
    <jsp:include page="left.jsp" />
    <aside class="right-side">
        <section class="content">
            <div class="admin-main">
                    <div class="admin-bd">
                        <div class="user-box home1">
                            <div class="administrator clearfix administrator-margin">
                                <div class="group-item">
                                    <label class="gx-texttitle">
                                        专栏名称：
                                    </label>
                                    <input id="qColumnName" type="text" class="gx-textbox" placeholder="请输入专栏名称">
                                </div>
                                <button id="query" class="gx-button gx-button-info gx-button-small admin-btn fr">查询</button>
                            </div>
                            <div class="admin-table">
                                <table class="tab" id="datatable">
                                </table>
                                <jsp:include page="pager.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </aside>
</div>

<!-- 新建专栏弹窗 -->
<div class="popUpBox create-speColumnName">
    <h1 class="popUpBoxTit">
        新建专栏
        <a class="popUpBoxNo"  href="javascript:void(0)"></a>
    </h1>
    <div class="popUpBoxCon">
        <div class="newPop-menu">
            <ul>
                <li>
                    <label><span>*</span>专栏名称：</label>
                    <input id="addColumnName" name="columnName" type="text" class="newpop-tet"/>
                </li>
                <li>
                    <label><span>*</span>专栏描述：</label>
                    <input id="addColumnDesc" name="columnDesc" type="text" class="newpop-tet"/>
                </li>
            </ul>
            <div class="addCancle">
                <button id="addSpeColumnBtn" class="gx-button gx-button-info">添加</button>
                <button class="gx-button gx-button-error popUpBoxNo">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 专栏编辑 -->
<div class="popUpBox edit-speColumnName">
    <h1 class="popUpBoxTit">
        专栏信息编辑
        <a class="popUpBoxNo"  href="javascript:void(0)"></a>
    </h1>
    <div class="popUpBoxCon">
        <div class="newPop-menu">
            <ul>
                <li>
                    <input id="editId" name="id" type="hidden" value=""/>
                    <label><span>*</span>专栏名称：</label>
                    <input id="editColumnName" name="columnName" class="newpop-tet"/>
                </li>
                <li>
                    <label><span>*</span>专栏描述：</label>
                    <input id="editColumnDesc" name="columnDesc" class="newpop-tet"/>
                </li>
            </ul>
            <div class="addCancle">
                <button id="updateSpeColumnBtn" class="gx-button gx-button-info">更新</button>
                <button class="gx-button gx-button-error popUpBoxNo">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="js/admin_yc.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language:  'zh-CN'
    });

</script>
<script src="js/page.js"></script>
<script src="js/web/specolumn_manage.js"></script>
<jsp:include page="footer.jsp" />