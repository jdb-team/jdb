#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/7 17:43
import user, topic, cmt, admin
import alumnus, activity, friend
import circle, pic, message, focus
import inform, topictype, specolumn
import verifycode,invitatecode


def initdata():
    """初始化数据"""
    user.inituser()
    admin.initAdmin()
    specolumn.initspecolumn()
    topictype.inittopictype()
    topic.initTopic()
    pic.resetPicPath("topic")
    cmt.initTopicCmt()
    alumnus.initAlumnus()
    pic.resetPicPath("alumnus")
    activity.initActivity()
    pic.resetPicPath("activity")
    cmt.initAtvCmt()
    friend.initFriends()
    circle.initCircle()
    pic.resetPicPath("circle")
    cmt.initCirCmt()
    message.initmessage()
    focus.initfocus()
    pic.resetPicPath("focus")
    inform.initinform()
    inform.resetInformUser()
    verifycode.initVerifyCode()
    invitatecode.initInvitatecode()


if __name__ == '__main__':
    initdata()
