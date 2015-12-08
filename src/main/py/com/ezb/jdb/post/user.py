#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib, urllib2

from poster.encode import multipart_encode
from poster.streaminghttp import register_openers


def login():
    """登录"""
    url = "http://localhost:8088/jdb/mobile/user/login"
    params = {"username": "13327689964", "password": "100001"}
    post_data = urllib.urlencode(params)
    response = urllib2.urlopen(url, post_data)
    content = response.read()
    print content
    return response.headers["x-access-token"]


def perfinfo(token):
    """完善个人信息"""
    url = "http://localhost:8088/jdb/mobile/user/perfinfo"
    register_openers()
    params = {
        "phone": "13327689964",
        "headPicPath":"aaa"
    }
    datagen, headers = multipart_encode(params)
    request = urllib2.Request(url, datagen, headers)
    request.add_header("x-access-token", token)
    return urllib2.urlopen(request).read()


if __name__ == '__main__':
    print perfinfo(login())
