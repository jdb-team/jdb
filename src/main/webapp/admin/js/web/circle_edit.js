var basePath = getBasePath();

function bindData() {
    var obj = ajax("/pc/admin/circle/view", {id: $("#id").val()});
    $("#title").val(obj.data.title);
    $("#introduce").val(obj.data.introduce);
    $("#picpathShow").attr("src", basePath + "/mobile/image/showimage?picpath=" + obj.data.picPath);
    $("#iconpathShow").attr("src", basePath + "/mobile/image/showimage?picpath=" + obj.data.iconPath);
}

bindData();
$("#introduce").qeditor({});

$("#pic").uploadPreview({ Img: "picpathShow", Width: 400, Height: 200 });
$("#icon").uploadPreview({ Img: "iconpathShow", Width: 80, Height: 80 });

//提交表单
$('#submitBtn').on('click', function (e) {
    e.preventDefault();
    $("#form1").ajaxSubmit({
        url: basePath + "/pc/admin/circle/save",
        type: "post",
        success: function (data) {
            alert($.parseJSON(data).error);
            window.location.href = "circle_list.jsp";
        }
    });
});

//取消
$('#cancelBtn').on('click', function (e) {
    e.preventDefault();
    bindData();
});