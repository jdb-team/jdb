var basePath = getBasePath();
$(function () {
    $("#verifycodeImg").click(function () {
        $("#verifycodeImg").attr("src", chgUrl(basePath + "/login/verifycode"));
    });

    //表单校验
    var validate = $("#admin-login").validate({
        debug: true,
        focusInvalid: false,
        onkeyup: false,
        submitHandler: function (form) {

            var obj = ajax(
                "/pc/login/dologin",
                {
                    username: $("#username").val(),
                    password: $("#password").val()
                }
            );
            if (obj.code == "0") {

                //记住登录状态
                if ($('#rmbUser').is(':checked')) {
                    var userName = $("#username").val();
                    var passWord = $("#password").val();
                    $.cookie("rmbUser", "true", {expires: 7}); // 存储一个带7天期限的 cookie
                    $.cookie("username", userName, {expires: 7}); // 存储一个带7天期限的 cookie
                    $.cookie("password", passWord, {expires: 7}); // 存储一个带7天期限的 cookie
                }else {
                    $.cookie("rmbUser", "false", {expires: -1});
                    $.cookie("username", '', {expires: -1});
                    $.cookie("password", '', {expires: -1});
                }

                window.location = basePath + "/admin/index.jsp";
            } else {
                alert(obj.error);
            }
        },

        rules: {
            username: {
                required: true
            },
            password: {
                required: true,
                rangelength: [2, 20]
            },
            verifycode: {
                required: true,
                rangelength: [4, 4],
                remote: {
                    url: basePath + "/login/checkverifycode",
                    type: "get",
                    dataType: "json"
                }
            }
        },
        messages: {
            username: {
                required: "必填"
            },
            password: {
                required: "必填",
                rangelength: $.format("密码长度为2~20位")
            },
            verifycode: {
                required: "不能为空",
                remote: $.format("验证码错误。"),
                rangelength: $.format("验证码长度必须为4。"),
            }
        }
    });

    if ($.cookie("rmbUser") == "true") {
        $("#rmbUser").attr("checked", true);
        $("#username").val($.cookie("username"));
        $("#password").val($.cookie("password"));
    }
});

function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    urlurl = url.substring(0, 17);
    if ((url.indexOf("?") >= 0)) {
        urlurl = url + "&t=" + timestamp;
    } else {
        urlurl = url + "?t=" + timestamp;
    }
    return urlurl;
}
