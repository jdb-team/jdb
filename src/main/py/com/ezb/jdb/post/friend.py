#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib, urllib2, user


def release(token):
    """解除好友关系"""
    url = "http://localhost:8000/jdb/mobile/friend/release"
    params = {"phone1": "11111130", "phone2": "11111155"}
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content


if __name__ == '__main__':
    token = user.login()
    release(token)
