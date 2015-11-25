var basePath = getBasePath();

$("#pic").uploadPreview({Img: "picpathShow", Width: 400, Height: 200});
$("#icon").uploadPreview({Img: "iconpathShow", Width: 80, Height: 80});

$("#introduce").qeditor({});

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