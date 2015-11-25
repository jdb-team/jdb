#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:53
import initconn,random

def initAlumnus():
    """用户详细信息表"""
    conn = initconn.getConn()
    cur = conn.cursor()

    for i in range(1,101):
        value = [
            "company" + str(i),
            "department" + str(i),
            "email" + str(i) + "@qq.com",
            "enrealname" + str(i),
            "grade" + str(i),
            "headpicpath" + str(i),
            str(round(random.random() * 360, 2)),
            str(round(random.random() * 360, 2)),
            "111111" + str(i),
            "realname" + str(i),
            "school" + str(i),
            random.randint(0, 1),
            "title" + str(i),
            "weixin" + str(i),
            "remark" + str(i),
            str(i)
        ]
        cur.execute('insert into alumnus(company,department,email,enrealname,grade,'
                    'headpic_path,lat,lng,phone,realname,school,sex,title,weixin,remark,user_id) '
                    'values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)', value)

    value = [
        "company102",
        "department102",
        "email102@qq.com",
        "enrealname102",
        "grade102",
        "headpicpath102",
        str(round(random.random() * 360, 2)),
        str(round(random.random() * 360, 2)),
        "13691513507",
        "realname102",
        "school102",
        random.randint(0, 1),
        "title102",
        "weixin102",
        "remark102",
        "102"
    ]

    cur.execute('insert into alumnus(company,department,email,enrealname,grade,headpic_path,'
                'lat,lng,phone,realname,school,sex,title,weixin,remark,user_id) '
                'values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)', value)

    conn.commit()
    cur.close()
    conn.close()
