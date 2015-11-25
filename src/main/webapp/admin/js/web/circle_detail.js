var basePath = getBasePath();

function bindData() {
    var obj = ajax("/pc/admin/circle/viewcircle", {id: $("#id").val()});

    $("#circle-detail").empty();
    $("#circle-detail").html(
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>圈子编号：</span>" + obj.data.id + "</li>" +
        "<li><span>圈子名称：</span>" + obj.data.title + "</li>" +
        "<li><span>创建时间：</span>" + obj.data.createTime + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<span>图片：</span>" +
        "<p class=\"content\">" +
        "<img width=\"920\" height=\"517.5\" src=\"" + basePath + "/mobile/image/showimage?picpath=" + obj.data.picPath + "\"/>" +
        "</p>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<span>内容：</span>" +
        "<p class=\"content\">" + obj.data.introduce + "</p>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft\">" +
        "<li><span>创建者账号：</span>" + obj.data.createUser.username + "</li>" +
        "<li><span>创建者：</span>" + obj.data.createUser.realName + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil-btn\">" +
        "<button onclick=\"javascript:history.go(-1)\" class=\"gx-button gx-button-error gx-button-small\">返回</button>" +
        "</div>"
    );
}

bindData();

