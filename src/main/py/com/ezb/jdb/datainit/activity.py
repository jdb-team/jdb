#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:59
import initconn,time,random

def initActivity():
    """活动信息表"""
    conn = initconn.getConn()
    cur = conn.cursor()

    for i in range(1,101):
        value = [
            "activitydesc" + str(i),
            "address" + str(i),
            "city" + str(i),
            "contactemail" + str(i) + "@qq.com",
            "contactman" + str(i),
            "contactphone" + str(i),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            "joinfee" + str(i),
            "picpath" + str(i),
            random.randint(1, 100),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            str(1),
            "title" + str(i),
            str(i),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            "otherinfo" + str(i),
            random.randint(1, 100),
            "topic" + str(i)
        ]
        cur.execute('insert into activity(activitydesc,address,city,contactemail,'
                    'contactman,contactphone,c_time,e_time,joinfee,'
                    'picpath,pv,s_time,state,title,create_userid,close_time,otherinfo,personlimit,topic) '
                    'values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)', value)
    conn.commit()
    cur.close()
    conn.close()
