var basePath = getBasePath();

function bindData() {
    var obj = ajax("/pc/admin/user/view", {username: $("#username-detail").val()});
    var sex = "男";
    if (obj.data.alumnus.sex = 0) {
        sex = "女";
    }

    $("#user-detail").empty();
    $("#user-detail").html(
        "<div class=\"notice fl\">" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>姓名：</span>" + obj.data.alumnus.realName + "</li>" +
        "<li><span>英文名：</span>" + obj.data.alumnus.enRealName + "</li>" +
        "<li><span>邮箱：</span>" + obj.data.alumnus.email + "</li>" +
        "</ul>" +
        "<ul class=\"detdilCenter fl\">" +
        "<li><span>性别：</span>" + sex + "</li>" +
        "<li><span>电话：</span>" + obj.data.alumnus.phone + "</li>" +
        "<li><span>微信：</span>" + obj.data.alumnus.weixin + "</li>" +
        "</ul>" +
        "<div class=\"detail-right fr\">" +
        "<img width=\"220\" height=\"220\" src=\"" + basePath + "/mobile/image/showimage?picpath=" + obj.data.alumnus.headPicPath + "\"/>" +
        "</div>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>学校：</span>" + obj.data.alumnus.school + "</li>" +
        "<li><span>系院：</span>" + obj.data.alumnus.department + "</li>" +
        "</ul>" +
        "<ul class=\"detdilCenter fl\">" +
        "<li><span>年级：</span>" + obj.data.alumnus.grade + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil\">" +
        "<ul class=\"detdilLeft fl\">" +
        "<li><span>职位：</span>" + obj.data.alumnus.title + "</li>" +
        "<li><span>公司：</span>" + obj.data.alumnus.company + "</li>" +
        "</ul>" +
        "</div>" +
        "<div class=\"group-detdil-btn\">" +
        "<button onclick=\"history.go(-1)\" class=\"gx-button gx-button-error gx-button-small\">返回</button>" +
        "</div>" +
        "</div>"
    );
}

bindData();

