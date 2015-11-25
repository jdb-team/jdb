#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib, urllib2
import user


def join(token):
    """加入圈子"""
    url = "http://localhost:8000/jdb/mobile/circle/join"
    params = {"phone": "1111111", "id": "1"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    return content

def nickname(token):
    """更改昵称"""
    url = "http://localhost:8000/jdb/mobile/circle/nickname"
    params = {"id": "8","nickName":"我的昵称"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    return content


if __name__ == '__main__':
    token = user.login()
    # print join(token)
    print nickname(token)
