#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 17:01
import initconn, time, random


def initFriends():
    """好友信息表"""
    conn = initconn.getConn()
    cur = conn.cursor()

    for i in range(1,101):
        value = [
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            random.randint(0, 1000),
            random.randint(0, 1),
            random.randint(1, 100),
            random.randint(1, 100)
        ]
        cur.execute('insert into friend(a_time,c_time,distance,state,friend_id,user_id) values(%s,%s,%s,%s,%s,%s)', value)
    conn.commit()
    cur.close()
    conn.close()
