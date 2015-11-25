#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 17:00
import initconn, time, random


def initCmt(table):
    conn = initconn.getConn()
    cur = conn.cursor()
    circleIds = [0] * 11  # 定义长度为10的数组

    # 先插入10条一级评论
    for i in range(1,11):
        circleIds[i] = random.randint(1, 101)
        value = [
            "content" + str(i),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            random.randint(1, 100),
            random.randint(1, 100),
            random.randint(1, 100)
        ]
        if table == "Topiccmt":
            value[5] = circleIds[i]
        if table == "circmt" or table == "atvcmt":
            value[4] = circleIds[i]
        cur.execute('insert into ' + table + ' values(%s,%s,%s,%s,%s,null)', value)
    conn.commit()

    # 插入90条 子评论
    for i in range(12, 101, 1):
        parentCmtId = random.randint(1, 10)
        value = [
            "content" + str(i),
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            random.randint(1, 100),
            random.randint(1, 100),
            random.randint(1, 100),
            parentCmtId
        ]
        if table == "Topiccmt":
            value[5] = circleIds[parentCmtId]
        if table == "circmt" or table == "atvcmt":
            value[4] = circleIds[parentCmtId]
        cur.execute('insert into ' + table + ' values(%s,%s,%s,%s,%s,%s)', value)
    conn.commit()
    cur.close()
    conn.close()


def initTopicCmt():
    """话题评论表"""
    initCmt("Topiccmt(content,c_time,likecount,comment_userid,Topic_id,parent_id)")

def initAtvCmt():
    """活动评论表"""
    initCmt("atvcmt(content,c_time,likecount,activity_id,comment_userid,parent_id)")


def initCirCmt():
    """圈子评论"""
    initCmt("circmt(content,c_time,likecount,circle_id,comment_userid,parent_id)")