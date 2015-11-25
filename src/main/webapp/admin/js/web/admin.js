var basePath = getBasePath();
// 创建管理员
function createAdmin() {
    $(".create-admin").popUpBox({
        fn: function () {
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}

// 编辑管理员
function editAdmin(id) {
    $(".edit-admin").popUpBox({
        fn: function () {
            var obj = ajax("/pc/admin/admin/viewadmin", {id: id});
            if (obj.code != "0") {
                alert(obj.error);
            } else {
                $("#editId").val(obj.data.id);
                $("#editUserName").html(obj.data.username);
                $("#editPassword").val(obj.data.password);
                $("#editPassword1").val(obj.data.password);
                $("#editRealName").val(obj.data.realName);
                $("#editPhone").val(obj.data.phone);
            }
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}

//数据加载
function binddata(curpage, pageSize) {
    var obj = ajax(
        "/pc/admin/admin/query",
        {
            curPage: curpage,
            pageSize: pageSize,
            username: $("#qusername").val(),
            realName: $("#qrealName").val(),
            startTime: $("#qstartTime").val(),
            endTime: $("#qendTime").val()
        }
    );

    $("#pageCount").val(obj.data.pageCount);

    $("#datatable").empty();
    $("#datatable").append(
        "<tr class=\"tab-h\">" +
        "<td class=\"tab-first\">序号</td>" +
        "<td class=\"tab-two\">ID</td>" +
        "<td class=\"tab-three\">账号</td>" +
        "<td class=\"tab-four\">姓名</td>" +
        "<td class=\"tab-five\">手机号</td>" +
        "<td class=\"tab-six\">注册时间</td>" +
        "<td class=\"tab-seven\">操作</td>" +
        "</tr>"
    );

    for (var i = 0; i < obj.data.resultList.length; i++) {
        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td class=\"tab-first\">" + obj.data.resultList[i].id + "</td>" +
            "<td class=\"tab-two\">" + obj.data.resultList[i].username + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].realName + "</td>" +
            "<td class=\"tab-four\">" + obj.data.resultList[i].phone + "</td>" +
            "<td class=\"tab-five\">" + obj.data.resultList[i].createTime + "</td>" +
            "<td class=\"tab-six\">" +
            "<b class=\"gx-button gx-button-info gx-button-actived gx-button-small edit-admin-btn\">编辑</b> " +
            "<b class=\"delAdminBtn gx-button gx-button-error gx-button-actived gx-button-small\">删除</b>" +
            "</td>" +
            "</tr>"
        );
    }

    $(".delAdminBtn").on('click', function (e) {
        var name = $(this).parents('tr').children('.tab-three').text();
        if (confirm("删除管理员也将删除该管理员创建的圈子信息,确定删除管理员(" + name + ")?")) {
            var id = $(this).parents('tr').children('.tab-first').text();
            var obj = ajax("/pc/admin/admin/delete", {ids: id});
            alert(obj.error);
            binddata($("#curPage").val(), pageSize);
            bindPage(5, $("#curPage").val(), $("#pageCount").val());
        }
    })

    $('.edit-admin-btn').on('click', function (e) {
        e.preventDefault();
        var id = $(this).parents('tr').children('.tab-first').text();
        editAdmin(id);
    })
}

$('#cAdmin').on('click', function (e) {
    e.preventDefault();
    createAdmin();
});


$('#query').on('click', function (e) {
    e.preventDefault();
    binddata(1, pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
})

$('#reset').on('click', function (e) {
    e.preventDefault();
    $("#qusername").val("");
    $("#qrealName").val("");
    $("#qstartTime").val("");
    $("#qendTime").val("");
})

$('#updataAdminBtn').on('click', function (e) {
    e.preventDefault();
    if ($("#editPassword").val() != $("#editPassword1").val()) {
        alert("两次输入的密码不同!");
    } else {
        var obj = ajax(
            "/pc/admin/admin/saveorupdate",
            {
                id: $("#editId").val(),
                password: $("#editPassword").val(),
                realName: $("#editRealName").val(),
                phone: $("#editPhone").val()
            }
        );
        alert(obj.error);
        binddata($("#curPage").val(), pageSize);
    }
});

$('#addAdminBtn').on('click', function (e) {
    e.preventDefault();

    if ($("#addPassword").val() != $("#addPassword1").val()) {
        alert("两次输入的密码不同!");
    }else{
        var obj = ajax(
            "/pc/admin/admin/saveorupdate",
            {
                username: $("#addUsername").val(),
                password: $("#addPassword").val(),
                realName: $("#addRealName").val(),
                phone: $("#addPhone").val()
            }
        );
        alert(obj.error);
        binddata($("#curPage").val(), pageSize);
        bindPage(5, $("#curPage").val(), $("#pageCount").val());
    }
});

binddata(1, pageSize);
bindPage(5, $("#curPage").val(), $("#pageCount").val());

