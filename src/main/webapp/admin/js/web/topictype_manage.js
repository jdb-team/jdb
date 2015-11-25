var basePath = getBasePath();
// 创建管理员
function createTopicType() {
    $(".create-topicType").popUpBox({
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
    $(".edit-topicType").popUpBox({
        fn: function () {
            var obj = ajax("/pc/admin/topictype/view", {id: id});
            if (obj.code != "0") {
                alert(obj.error);
            } else {
                $("#editId").val(obj.data.id);
                $("#editTypeName").val(obj.data.typeName);
                $("#editTypeDesc").val(obj.data.typeDesc);
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
        "/pc/admin/topictype/query",
        {
            curPage: curpage,
            pageSize: pageSize,
            typeName: $("#qtypeName").val(),
        }
    );

    $("#pageCount").val(obj.data.pageCount);

    $("#datatable").empty();
    $("#datatable").append(
        "<tr class=\"tab-h\">" +
        "<td class=\"tab-first\">序号</td>" +
        "<td class=\"tab-two\">ID</td>" +
        "<td class=\"tab-three\">分类名称</td>" +
        "<td class=\"tab-four\">分类描述</td>" +
        "<td class=\"tab-seven\">操作</td>" +
        "</tr>"
    );

    for (var i = 0; i < obj.data.resultList.length; i++) {
        $("#datatable").append(
            "<tr>" +
            "<td>" + ((i + 1) + (curpage - 1) * pageSize) + "</td>" +
            "<td class=\"tab-first\">" + obj.data.resultList[i].id + "</td>" +
            "<td class=\"tab-two\">" + obj.data.resultList[i].typeName + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].typeDesc + "</td>" +
            "<td class=\"tab-six\">" +
            "<b class=\"gx-button gx-button-info gx-button-actived gx-button-small edit-admin-btn\">编辑</b> " +
            "<b class=\"delTopicTypeBtn gx-button gx-button-error gx-button-actived gx-button-small\">删除</b>" +
            "</td>" +
            "</tr>"
        );
    }

    $(".delTopicTypeBtn").on('click', function (e) {
        var name = $(this).parents('tr').children('.tab-three').text();
        if (confirm("删除分类也将删除该分类下的所有话题,确定删除分类(" + name + ")?")) {
            var id = $(this).parents('tr').children('.tab-first').text();
            var obj = ajax("/pc/admin/topictype/delete", {id: id});
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

$('#cTopicType').on('click', function (e) {
    e.preventDefault();
    createTopicType();
});


$('#query').on('click', function (e) {
    e.preventDefault();
    binddata(1, pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
})

$('#reset').on('click', function (e) {
    e.preventDefault();
    $("#qTypeName").val("");
})

$('#updateTopicTypeBtn').on('click', function (e) {
    e.preventDefault();
    var obj = ajax(
        "/pc/admin/topictype/update",
        {
            id: $("#editId").val(),
            typeName: $("#editTypeName").val(),
            typeDesc: $("#editTypeDesc").val()
        }
    );
    alert(obj.error);
    binddata($("#curPage").val(), pageSize);
});

$('#addTopicTypeBtn').on('click', function (e) {
    e.preventDefault();

    var obj = ajax(
        "/pc/admin/topictype/add",
        {
            typeName: $("#addTypeName").val(),
            typeDesc: $("#addTypeDesc").val()
        }
    );
    alert(obj.error);
    binddata($("#curPage").val(), pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
});

binddata(1, pageSize);
bindPage(5, $("#curPage").val(), $("#pageCount").val());

