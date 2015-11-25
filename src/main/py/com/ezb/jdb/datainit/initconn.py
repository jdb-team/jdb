#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:47
import MySQLdb


def getConn():
    return MySQLdb.connect(
        host='127.0.0.1',
        user='root', passwd='root',
        db='jdb', port=3306, charset="utf8"
    )

    # return MySQLdb.connect(
    #     host='172.16.16.235',
    #     user='jdb_write', passwd='jdb123879',
    #     db='jdb', port=3306, charset="utf8"
    # )
