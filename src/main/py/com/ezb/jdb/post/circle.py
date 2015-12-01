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

def query(token):
    """圈子列表"""
    url = "http://localhost:8088/jdb/mobile/circle/querycircles"
    params = {"phone": "10000000008"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    return content

def view(token):
    """查看详情"""
    url = "http://localhost:8080/jdb/mobile/circle/viewcircle"
    params = {"phone": "10000000008","cid":"1"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    return content


if __name__ == '__main__':
    token = user.login()
    print view(token)
