var basePath = getBasePath();
//数据加载
function binddata(curpage, pageSize) {

    var params = {
        curPage: curpage,
        pageSize: pageSize,
        username: $("#qusername").val(),
        realName: $("#qrealname").val(),
        email: $("#qemail").val(),
        startTime: $("#qstartTime").val(),
        endTime: $("#qendTime").val(),
        grade: $("#qgrade").val(),
        department: $("#qdepartment").val()
    };

    if ($("#stateslt").val() == "停用") {
        params.state = 0;
    }

    if ($("#stateslt").val() == "正常") {
        params.state = 1;
    }

    if ($("#sexslt").val() == "男") {
        params.sex = 1;
    }

    if ($("#sexslt").val() == "女") {
        params.sex = 0;
    }

    var obj = ajax("/pc/admin/user/query", params);
    $("#pageCount").val(obj.data.pageCount);
    $("#datatable tbody tr").eq(0).nextAll().remove();
    for (var i = 0; i < obj.data.resultList.length; i++) {
        var stateStr = "正常";
        var handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-error gx-button-small\">停用</b>";
        if (0 == obj.data.resultList[i].state) {
            stateStr = "停用";
            handleBtn = "<b onclick=\"state('" + obj.data.resultList[i].id + "')\" class=\"gx-button gx-button-warning gx-button-small\">启用</b>";
        }
        var sexStr = "未填写";
        if (1 == obj.data.resultList[i].alumnus.sex) {
            sexStr = "男";
        }
        if (0 == obj.data.resultList[i].alumnus.sex) {
            sexStr = "女";
        }
        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td>" + obj.data.resultList[i].id + "</td>" +
            "<td>" + obj.data.resultList[i].username + "</td>" +
            "<td>" + stateStr + "</td>" +
            "<td>" + obj.data.resultList[i].alumnus.realName + "</td>" +
            "<td>" + sexStr + "</td>" +
            "<td>" + obj.data.resultList[i].alumnus.email + "</td>" +
            "<td>" + obj.data.resultList[i].alumnus.school + "</td>" +
            "<td>" + obj.data.resultList[i].alumnus.grade + "</td>" +
            "<td>" + obj.data.resultList[i].alumnus.department + "</td>" +
            "<td>" + obj.data.resultList[i].createTime + "</td>" +
            "<td class=\"tab-Nine\">" +
            "<a href=\"user_detail.jsp?username=" + obj.data.resultList[i].username + "\" class=\"gx-button gx-button-info gx-button-actived gx-button-small\">详情</a> " +
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
 *  停用用户
 */
function state(id) {
    if (confirm("确定停用/启用此用户?")) {
        var obj = ajax("/pc/admin/user/state", {id: id});
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

$('#stateslt,#sexslt').on('change', function (e) {
    e.preventDefault();
    loadPage(1);
});