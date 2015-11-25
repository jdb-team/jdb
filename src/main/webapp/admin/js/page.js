var pageSize = 10;

function bindPage(innerPageSize, curPage, pageCount) {
    bindPage(innerPageSize,curPage,pageCount,pageSize);
}

/**
 * 初始化分页
 * @param innerPageSize 展示页面数
 * @param curPage 当前页面
 * @param pageCount 总页数
 */
function bindPage(innerPageSize, curPage, pageCount,pageSize) {
    $("#pageDiv").empty();

    //页面大小
    var innerPageCount = parseInt(pageCount / innerPageSize);
    if (pageCount % innerPageSize != 0) {
        innerPageCount += 1;
    }

    //当前页
    var innerCurPage = parseInt(curPage / innerPageSize) + 1;
    if (curPage % innerPageSize == 0) {
        innerCurPage -= 1;
    }

    var startPage = (innerCurPage - 1) * innerPageSize + 1;
    if (startPage == 1) {
        $("#pageDiv").append("<a class=\"gx-pager-disabled\" href=\"###\"><i class=\"gx-icon\">«</i></a>");
    } else {
        $("#pageDiv").append("<a class=\"gx-icon\" href=\"javascript:bindCurPage("
            + (innerCurPage - 1) * innerPageSize + ")\"><i class=\"gx-icon\">«</i></a>");
    }

    for (var i = startPage; i < startPage + innerPageSize && i <= pageCount; i++) {
        if (i == curPage) {
            $("#pageDiv").append("<a class=\"gx-pager-actived\" href=\"javascript:bindCurPage(" + i + ")\">" + i + "</a>");
        } else {
            $("#pageDiv").append("<a href=\"javascript:bindCurPage(" + i + ")\">" + i + "</a>");
        }
    }

    if (innerCurPage == innerPageCount) {
        $("#pageDiv").append("<a class=\"gx-pager-disabled\" href=\"###\"><i class=\"gx-icon\">»</i></a>");
    } else {
        $("#pageDiv").append("<a href=\"javascript:bindCurPage("
            + (innerCurPage * innerPageSize + 1) + ")\"><i class=\"gx-icon\">»</i></a>");
    }
}

function bindCurPage(curPage) {
    $("#curPage").val(curPage);
    binddata(curPage, pageSize);
    bindPage(5, $("#curPage").val(), $("#pageCount").val());
}
