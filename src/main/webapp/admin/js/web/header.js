var basePath = getBasePath();
$('#editPass').on('click', function (e) {
    e.preventDefault();
    editPass();
})

$('#updateBtn').on('click', function (e) {
    if ($('#password').val() != $('#password1').val()) {
        alert("两次输入密码不同!");
    } else {
        var obj = ajax("/pc/admin/admin/updatepass", {password: $("#password").val()});
        alert(obj.error);
    }
})

// 加载当前用户用户名
function editPass() {
    $(".edit-pass").popUpBox({
        fn: function () {
            var obj = ajax("/pc/admin/admin/querycuradmin", null);
            if (obj.code == "0") {
                $("#username").empty();
                $("#username").html(obj.data.username);
            } else {
                alert(obj.error);
            }
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}