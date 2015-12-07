#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib
import urllib2
import user


def search(token):
    """查看活动详情"""
    url = "http://localhost:8088/jdb/mobile/global/search"
    params = {
        "phone": "13327689964",
        "keyword": "1",
        "circlePageResult.pageSize": "5",
        "activityPageResult.pageSize": "5",
        "friendPageResult.pageSize": "5",
        "topicPageResult.pageSize": "5"
    }
    post_data = urllib.urlencode(params)
    request = urllib2.Request(url, post_data)
    request.add_header("x-access-token", token)
    content = urllib2.urlopen(request).read()
    print content


if __name__ == '__main__':
    token = user.login()
    search(token)
