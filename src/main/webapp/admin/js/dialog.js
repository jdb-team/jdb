/**
 * @description: dialog
 * @author: lixinwei
 * @version: V1
 * @update: 15/7/24
 */

/* 弹出框
 * fn : 当弹出框出现时，执行的函数
 * fnY : 当弹出框确定时执行的函数，当该函数返回为true时不关闭弹出框 默认为false
 * fnN : 当弹出框被取消时执行的函数
 */
$.fn.popUpBox = function (p) {
    var d = {
            fn: function () {
            },
            fnY: function () {
            },
            fnN: function () {
            }
        },
        o = $.extend(d, p || {}),
        bg = $('<div class="popUpBoxBg"></div>'),
        $this = $(this),
        _this = this
        ;
    $this.before(bg);

    _this.close = function () {
        bg.remove();
        $this.hide();
    }
    $this
        .find(".popUpBoxYes")
        .unbind("click")
        .click(function (e) {
            if (!o.fnY()) _this.close();
        })
    ;
    $this
        .find(".popUpBoxNo")
        .unbind("click")
        .click(function (e) {
            if (!o.fnN()) _this.close();
        })
    ;
    $this
        .css({
            "margin-left": -$this.outerWidth() / 2,
            "margin-top": -$this.outerHeight() / 2
        })
        .show()
    ;
    o.fn();
}
//状态弹出
function statusDialog(v) {
    $(".status-admin").popUpBox({
        fn: function () {
            $('.status-conts').find('p').text(v);
        },
        fnN: function () {
        },
        fnY: function () {
        }
    });
}
//提交发布提示
$('#submit-succ').on('click', function (e) {
    e.preventDefault();
    var str = "成功！";
    statusDialog(str);
    setTimeout(function () {
        $(".popUpBoxBg").remove();
        $(".status-admin").hide();

    }, 3000)

})