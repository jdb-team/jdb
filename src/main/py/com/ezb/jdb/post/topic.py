#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib, urllib2,user

from poster.encode import multipart_encode
from poster.streaminghttp import register_openers


def createTopic(token):
    """创建话题"""
    register_openers()
    params = {"content": "创建话题", "topicType.id": "1", "phone": "1111111", "pic1": open("aa.png", "rb"),
              "pic2": open("bb.png", "rb")}
    datagen, headers = multipart_encode(params)
    request = urllib2.Request("http://localhost:8000/jdb/mobile/topic/createtopic", datagen, headers)
    request.add_header("x-access-token", token)
    print urllib2.urlopen(request).read()


def sv(token):
    """新增分享"""
    url = "http://localhost:8000/jdb/mobile/topic/sv"
    params = {"phone": "1111111", "id": "1"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content

def lv(token):
    """点赞"""
    url = "http://localhost:8000/jdb/mobile/topic/lv"
    params = {"phone": "1111111", "id": "1"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content

def view(token):
    """查看详情"""
    url = "http://localhost:8088/jdb/mobile/topic/view"
    params = {"phone": "10000000046", "id": "1"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content

if __name__ == '__main__':
    token = user.login()
    view(token)
