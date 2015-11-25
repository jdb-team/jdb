#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/12 16:51
import urllib2

from poster.encode import multipart_encode
from poster.streaminghttp import register_openers

import user


def query(token):
    """话题分类列表"""
    register_openers()
    params = {}
    datagen, headers = multipart_encode(params)
    request = urllib2.Request("http://localhost:8000/jdb/mobile/topic/querytopictype", datagen, headers)
    request.add_header("x-access-token", token)
    return urllib2.urlopen(request).read()


if __name__ == '__main__':
    token = user.login()
    print query(token)