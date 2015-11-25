<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%String basepath = request.getContextPath();%>
<aside class="left-side">
    <section class="sidebar">
        <!-- 侧边菜单 begin -->
        <ul class="sidebar-menu">
            <li class="active">
                <a href="index.jsp">
                    <i class="fa fa-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <%
                if (request.getSession().getAttribute("isSuper") != null) {
            %>
            <li class="treeview active">
                <a href="administrator.jsp">
                    <i class="fa fa-gear"></i>
                    <span>系统管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a id="administrator" href="administrator.jsp"><i class="fa fa-angle-double-right"></i>管理员维护</a>
                    </li>
                </ul>
            </li>
            <%
                }
            %>
            <li class="treeview active">
                <a href="home_setting.jsp">
                    <i class="fa fa-list-ol"></i>
                    <span>业务管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a id="home_setting" href="home_setting.jsp"><i class="fa fa-angle-double-right"></i>发现管理</a>
                    </li>
                    <li><a id="report_manage" href="report_manage.jsp"><i class="fa fa-angle-double-right"></i>举报管理</a>
                    </li>
                    <li><a id="user_manage" href="user_manage.jsp"><i class="fa fa-angle-double-right"></i>用户管理</a></li>
                    <li><a id="activity_manage" href="activity_manage.jsp"><i class="fa fa-angle-double-right"></i>活动管理</a>
                    </li>
                    <li><a id="topic_manage" href="topic_manage.jsp"><i class="fa fa-angle-double-right"></i>话题管理</a>
                    </li>
                    <li><a id="topictype_manage" href="topictype_manage.jsp"><i class="fa fa-angle-double-right"></i>分类管理</a>
                    </li>
                    <li><a id="specolumn_manage" href="specolumn_manage.jsp"><i class="fa fa-angle-double-right"></i>专栏管理</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- 侧边菜单 end -->
    </section>
</aside>
<script src="js/left.js"></script>