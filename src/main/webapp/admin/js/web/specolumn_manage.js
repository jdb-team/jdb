var basePath = getBasePath();
// 创建专栏
function createSpeColumn() {
    $(".create-speColumnName").popUpBox({
        fn: function () {
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}

// 编辑专栏
function editSpeColumn(id) {
    $(".edit-speColumnName").popUpBox({
        fn: function () {
            var obj = ajax("/pc/admin/specolumn/view", {id: id});
            if (obj.code != "0") {
                alert(obj.error);
            } else {
                $("#editId").val(obj.data.id);
                $("#editColumnName").val(obj.data.columnName);
                $("#editColumnDesc").val(obj.data.columnDesc);
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
        "/pc/admin/specolumn/query",
        {
            curPage: curpage,
            pageSize: pageSize,
            columnName: $("#qColumnName").val(),
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
            "<td class=\"tab-two\">" + obj.data.resultList[i].columnName + "</td>" +
            "<td class=\"tab-three\">" + obj.data.resultList[i].columnDesc + "</td>" +
            "<td class=\"tab-six\">" +
            "<b class=\"gx-button gx-button-info gx-button-actived gx-button-small edit-specolumn-btn\">编辑</b> " +
            "<b class=\"delSpeColumnBtn gx-button gx-button-error gx-button-actived gx-button-small\">删除</b>" +
            "</td>" +
            "</tr>"
        );
    }

    $(".delSpeColumnBtn").on('click', function (e) {
        if (confirm("确定删除此专栏?")) {
            var id = $(this).parents('tr').children('.tab-first').text();
            var obj = ajax("/pc/admin/specolumn/delete", {id: id});
            alert(obj.error);
            binddata($("#curPage").val(), pageSize);
            bindPage(5, $("#curPage").val(), $("#pageCount").val());
        }
    })

    $('.edit-specolumn-btn').on('click', function (e) {
        e.preventDefault();
        var id = $(this).parents('tr').children('.tab-first').text();
        editSpeColumn(id);
    })
}

$('#cSpeColumn').on('click', function (e) {
    e.preventDefault();
    createSpeColumn();
});


$('#query').on('click', function (e) {
    e.preventDefault();
    binddata(1, pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
})

$('#reset').on('click', function (e) {
    e.preventDefault();
    $("#qColumnName").val("");
})

$('#updateSpeColumnBtn').on('click', function (e) {
    e.preventDefault();
    var obj = ajax(
        "/pc/admin/specolumn/update",
        {
            id: $("#editId").val(),
            columnName: $("#editColumnName").val(),
            columnDesc: $("#editColumnDesc").val()
        }
    );
    alert(obj.error);
    binddata($("#curPage").val(), pageSize);
});

$('#addSpeColumnBtn').on('click', function (e) {
    e.preventDefault();

    var obj = ajax(
        "/pc/admin/specolumn/add",
        {
            columnName: $("#addColumnName").val(),
            columnDesc: $("#addColumnDesc").val()
        }
    );
    alert(obj.error);
    binddata($("#curPage").val(), pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
});

binddata(1, pageSize);
bindPage(5, $("#curPage").val(), $("#pageCount").val());

