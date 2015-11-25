#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn, time
import random


def initmessage():
    """初始化消息表"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        for i in range(1,101):
            sender_id = str(random.randint(1, 100))
            receiver_id = str(random.randint(1, 100))

            # 插入消息表
            value = [
                "content" + str(i),
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                random.randint(0, 1),
                sender_id,
                receiver_id
            ]
            cur.execute('insert into message(content,c_time,state,receive_userid,sender_userid) values(%s,%s,%s,%s,%s)', value)

        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    initmessage()
