/**
 * Created by lixinwei on 15/6/11.
 */
(function($) {
    "use strict";

    $.fn.tree = function() {

        return this.each(function() {
            var btn = $(this).children("a").first();
            var menu = $(this).children(".treeview-menu").first();
            var isActive = $(this).hasClass('active');

            //initialize already active menus
            if (isActive) {
                menu.show();
                btn.children(".fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
            }
            //Slide open or close the menu on link click
            btn.click(function(e) {
                e.preventDefault();
                if (isActive) {
                    //Slide up to close menu
                    menu.slideUp();
                    isActive = false;
                    btn.children(".fa-angle-down").first().removeClass("fa-angle-down").addClass("fa-angle-left");
                    btn.parent("li").removeClass("active");
                } else {
                    //Slide down to open menu
                    menu.slideDown();
                    isActive = true;
                    btn.children(".fa-angle-left").first().removeClass("fa-angle-left").addClass("fa-angle-down");
                    btn.parent("li").addClass("active");
                    //add scroll bar
                    setTimeout(function(){
                        var sidebarHeight = $('.sidebar').height();
                        var showHeight = document.body.clientHeight -70;
                        if(sidebarHeight >= showHeight ){
                            btn.parents('.sidebar').css({
                                'height':showHeight,
                                'overflow-y':'scroll'
                            });
                        }
                    },500)
                }
            });
            /* Add margins to submenu elements to give it a tree look */
            menu.find("li > a").each(function() {
                var pad = parseInt($(this).css("margin-left")) + 10;

                $(this).css({"margin-left": pad + "px"});
            });
        });

    };

}(jQuery));

$(function(){
    "use strict";

    var $left = $('.left-side'),
        $right = $('.right-side');
    $(".sidebar .treeview").tree();
    $('.sidebar-toggle').on('click', function(){
        var _this = $(this);
        if(_this.hasClass('toggle')){
            _this.removeClass('toggle');
            $left.show();
            $right.css('marginLeft','190')
        } else{
            _this.addClass('toggle');
            $left.hide();
            $right.css('marginLeft','0')
        }

    });

})