var basePath = getBasePath();
//数据加载
function binddata(curpage, pageSize) {

    var params = {
        curPage: curpage,
        pageSize: pageSize,
        id: $("#qid").val(),
        title: $("#qtitle").val(),
        startTime: $("#qstartTime").val(),
        endTime: $("#qendTime").val(),
        username: $("#qusername").val(),
        realName: $("#qrealName").val()
    };

    if ($("#stateslt").val() == "冻结") {
        params.state = 0;
    }

    if ($("#stateslt").val() == "正常") {
        params.state = 1;
    }

    var obj = ajax("/pc/admin/activity/query", params);
    $("#pageCount").val(obj.data.pageCount);
    $("#datatable tbody tr").eq(0).nextAll().remove();
    for (var i = 0; i < obj.data.resultList.length; i++) {
        var stateStr = "正常";
        var handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-error gx-button-small\">冻结</b>";
        if (0 == obj.data.resultList[i].state) {
            stateStr = "冻结";
            handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-warning gx-button-small\">恢复</b>";
        }

        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td>" + obj.data.resultList[i].id + "</td>" +
            "<td>" + obj.data.resultList[i].title + "</td>" +
            "<td>" + stateStr + "</td>" +
            "<td>" + obj.data.resultList[i].createUser.username + "</td>" +
            "<td>" + obj.data.resultList[i].createUser.alumnus.realName + "</td>" +
            "<td>" + obj.data.resultList[i].createTime + "</td>" +
            "<td class=\"tab-Nine\">" +
            "<a href=\"activity_detail.jsp?id=" + obj.data.resultList[i].id + "\" class=\"gx-button gx-button-info gx-button-actived gx-button-small\">查看</a> " +
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

/**
 *  冻结活动
 */
function state(id) {
    if (confirm("确定冻结/恢复此用户?")) {
        var obj = ajax("/pc/admin/activity/state", {id: id});
        alert(obj.error);
        if (obj.code == "0") {
            loadPage($("#curPage").val());
        }
    }
}

loadPage(1);

$('#query').on('click', function (e) {
    e.preventDefault();
    loadPage(1);
});

$('#stateslt').on('change', function (e) {
    e.preventDefault();
    loadPage(1);
});