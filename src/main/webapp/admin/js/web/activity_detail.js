var basePath = getBasePath();

function bindData() {
    var obj = ajax("/pc/admin/activity/viewactivity", {id: $("#id").val()});

    $("#activity-detail").empty();
    $("#activity-detail").html(
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>活动编号：</span>" + obj.data.id + "</li>" +
        "<li><span>活动名称：</span>" + obj.data.title + "</li>" +
        "<li><span>举办地点：</span>" + obj.data.address + "</li>" +
        "</ul>" +
        "<ul class=\"detdilCenter fl\">" +
        "<li><span>创建时间：</span>" + obj.data.createTime + "</li>" +
        "<li><span>举办时间：</span>" + obj.data.startTime + "</li>" +
        "<li><span>结束时间：</span>" + obj.data.endTime + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<span>图片：</span>" +
        "<p class=\"content\">" +
        "<img width=\"920\" height=\"517.5\" src=\"" + basePath + "/mobile/image/showimage?picpath=" + obj.data.picPath + "\"/>" +
        "</p>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<span>活动介绍：</span>" +
        "<p class=\"content\">" + obj.data.activityDesc + "</p>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul>" +
        "<li><span>参会费用：</span>" + obj.data.joinFee + "元/人</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>联系人：</span>" + obj.data.contactMan + "</li>" +
        "<li><span>电话：</span>" + obj.data.contactPhone + "</li>" +
        "<li><span>Email：</span>" + obj.data.contactEmail + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft\">" +
        "<li><span>创建者账号：</span>" + obj.data.createUser.username + "</li>" +
        "<li><span>创建者：</span>" + obj.data.createUser.alumnus.realName + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil-btn\">" +
        "<button onclick=\"javascript:history.go(-1);\" class=\"gx-button gx-button-error gx-button-small\">返回</button>" +
        "</div>"
    );
}

bindData();

