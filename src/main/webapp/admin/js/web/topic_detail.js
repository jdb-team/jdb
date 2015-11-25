var basePath = getBasePath();

function bindData() {
    var obj = ajax("/pc/admin/topic/viewTopic", {id: $("#id").val()});
    var picStr = "";
    var picpaths = obj.data.picPath.split(",");
    for (var i = 0; i < picpaths.length; i++) {
        picStr += "<div class=\"group-detdil\">" +
            "<span>图片：</span>" +
            "<p class=\"content\">" +
            "<img width=\"920\" height=\"517.5\" src=\"" + basePath + "/mobile/image/showimage?picpath=" + picpaths[i] + "\"/>" +
            "</p>" +
            "</div>";
    }

    $("#topic-detail").empty();
    $("#topic-detail").html(
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>话题编号：</span>" + obj.data.id + "</li>" +
        "<li><span>话题类型：</span>" + obj.data.topicType.typeName + "</li>" +
        "<li><span>创建时间：</span>" + obj.data.createTime + "</li>" +
        "</ul>" +
        "</div>" +
        picStr +
        "<div class=\"group-detdil\">" +
        "<span>内容：</span>" +
        "<p class=\"content\">" + obj.data.content + "</p>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft\">" +
        "<li><span>创建者账号：</span>" + obj.data.createUser.username + "</li>" +
        "<li><span>创建者：</span>" + obj.data.createUser.alumnus.realName + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil-btn\">" +
        "<button onclick=\"javascript:history.go(-1)\" class=\"gx-button gx-button-error gx-button-small\">返回</button>" +
        "</div>"
    );
}

bindData();

