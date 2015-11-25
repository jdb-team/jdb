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

    if ($("#stateslt").val() == "下线") {
        params.state = 0;
    }

    if ($("#stateslt").val() == "正常") {
        params.state = 1;
    }

    if ($("#typeslt").val() != "话题类型") {
        params.type = $("#typeslt").val();
    }

    var obj = ajax("/pc/admin/topic/query", params);
    $("#pageCount").val(obj.data.pageCount);
    $("#datatable tbody tr").eq(0).nextAll().remove();
    for (var i = 0; i < obj.data.resultList.length; i++) {
        var stateStr = "正常";
        var handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-error gx-button-small\">下线</b>";
        if (0 == obj.data.resultList[i].state) {
            stateStr = "下线";
            handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-warning gx-button-small\">恢复</b>";
        }

        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td>" + obj.data.resultList[i].id + "</td>" +
            "<td>" + obj.data.resultList[i].topicType.typeName + "</td>" +
            "<td>" + stateStr + "</td>" +
            "<td>" + obj.data.resultList[i].createUser.username + "</td>" +
            "<td>" + obj.data.resultList[i].createUser.alumnus.realName + "</td>" +
            "<td>" + obj.data.resultList[i].createTime + "</td>" +
            "<td class=\"tab-Nine\">" +
            "<a href=\"topic_detail.jsp?id=" + obj.data.resultList[i].id + "\" class=\"gx-button gx-button-info gx-button-actived gx-button-small\">查看</a> " +
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
 *  冻结话题
 */
function state(id) {
    if (confirm("确定下线/恢复此用户?")) {
        var obj = ajax("/pc/admin/topic/state", {id: id});
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

$('#typeslt,#stateslt').on('change', function (e) {
    e.preventDefault();
    loadPage(1);
});