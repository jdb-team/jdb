#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:51
import time

import initconn, random


def initTopic():
    """初始化话题表"""
    conn = initconn.getConn()
    cur = conn.cursor()

    for i in range(1, 101):
        value = [
            "content" + str(i),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            "path" + str(i),
            str(1),
            str(1),
            str(1),
            str(1),
            random.randint(1, 5),
            str(i)
        ]
        cur.execute(
            'insert into topic(content,c_time,picpath,pv,lv,sv,state,type_id,create_userid)'
            ' values(%s,%s,%s,%s,%s,%s,%s,%s,%s)',
            value)
    conn.commit()
    cur.close()
    conn.close()


if __name__ == '__main__':
    initTopic()
