var basePath = getBasePath();

//数据加载
function binddata(curpage, pageSize) {

    var params = {
        curPage: curpage,
        pageSize: pageSize,
        username: $("#qusername").val(),
        realname: $("#qrealname").val(),
        qbusername: $("#qbusername").val(),
        qbrealName: $("#qbrealname").val(),
        startTime: $("#qstartTime").val(),
        endTime: $("#qendTime").val()
    };

    if ($("#reasonslt").val() != "举报类型") {
        params.reason = $("#reasonslt").val();
    }

    if ($("#typeslt").val() != "分类") {
        params.type = $("#typeslt").val();
    }

    if ($("#stateslt").val() == "已处理") {
        params.state = 1;
    }

    if ($("#stateslt").val() == "待处理") {
        params.state = 0;
    }

    var obj = ajax("/pc/admin/inform/query", params);
    $("#pageCount").val(obj.data.pageCount);
    $("#datatable tbody tr").eq(0).nextAll().remove();
    for (var i = 0; i < obj.data.resultList.length; i++) {
        var stateStr = "已处理";
        var handleBtn = "<b class=\"gx-button gx-button-info gx-button-small\">已处理</b>";
        if (0 == obj.data.resultList[i].state) {
            stateStr = "待处理";
            handleBtn = "<b onclick=\"offline('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-error gx-button-small\">下&nbsp;&nbsp;线</b>";
        }
        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td>" + obj.data.resultList[i].id + "</td>" +
            "<td class=\"tab-first\">" + obj.data.resultList[i].reason + "</td>" +
            "<td class=\"tab-two\">" + obj.data.resultList[i].type + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].createUser.alumnus.realName + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].informUser.alumnus.realName + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].otherInfo + "</td>" +
            "<td class=\"tab-four\">" + obj.data.resultList[i].createTime + "</td>" +
            "<td class=\"tab-five\">" + stateStr + "</td>" +
            "<td>" +
            "<b onclick=\"informInfo('" + obj.data.resultList[i].otherInfo + "')\" class=\"gx-button gx-button-warning gx-button-small\">描述</b> " +
            "<b onclick=\"view('" + obj.data.resultList[i].type + "','" + obj.data.resultList[i].assoId + "','" + obj.data.resultList[i].informUser.alumnus.phone + "')\" class=\"gx-button gx-button-info gx-button-small\">详情</b> " +
            handleBtn +
            "</td>" +
            "</tr>"
        );
    }
}

function loadPage(curPage) {
    binddata(curPage, pageSize);
    bindPage(5, curPage, $("#pageCount").val());
}

loadPage(1);

$('#query').on('click', function (e) {
    e.preventDefault();
    loadPage(1);
});

$('#reasonslt,#typeslt,#stateslt').on('change', function (e) {
    e.preventDefault();
    loadPage(1);
});

function informInfo(info) {
    $(".inform-info").popUpBox({
        fn: function () {
            $("#otherinfo").html(info);
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}

function offline(id) {
    if (confirm("确定下线举报对应的信息?")) {
        var obj = ajax("/pc/admin/inform/offline", {id: id});
        alert(obj.error);
        if (obj.code == "0") {
            loadPage($("#curPage").val());
        }
    }
}

function view(type, assoId, phone) {
    if (type == "话题") {
        window.location = "topic_detail.jsp?id=" + assoId;
    }
    if (type == "活动") {
        window.location = "activity_detail.jsp?id=" + assoId;
    }
    if (type == "圈子") {
        window.location = "circle_detail.jsp?id=" + assoId;
    }
    if (type == "用户") {
        window.location = "user_detail.jsp?username=" + phone;
    }
}