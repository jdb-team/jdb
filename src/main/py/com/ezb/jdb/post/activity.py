#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib, urllib2, user
from poster.encode import multipart_encode
from poster.streaminghttp import register_openers


def createActivity(token):
    """创建活动"""
    register_openers()
    params = {
        "title": "1111",
        "activityDesc": "活动描述",
        "phone": "13327689964",
        "city": "11111",
        "address": "address",
        "joinFee": "222",
        "personLimit": "333",
        "startTime": "2015-11-28 10:54:00",
        "endTime": "2015-11-28 10:54:00"
    }

    datagen, headers = multipart_encode(params)
    request = urllib2.Request("http://localhost:8088/jdb/mobile/activity/createactivity", datagen, headers)
    request.add_header("x-access-token", token)
    print urllib2.urlopen(request).read()


def viewactivity(token):
    """查看活动详情"""
    url = "http://localhost:8000/jdb/mobile/activity/viewactivity"
    params = {"phone": "13327689964", "id": "2"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content


if __name__ == '__main__':
    token = user.login()
    createActivity(token)
