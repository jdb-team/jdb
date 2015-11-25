#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn, time
import random, sys


def initinform():
    """初始化举报表"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        type = ["话题", "活动", "圈子"]
        viewurl = ["admin/topic/view", "admin/activity/view", "admin/circle/view"]
        reason = ["色情", "欺诈", "诋毁侮辱", "广告骚扰", "政治", "非交大校友", "其他"]

        for i in range(1,101):
            typeIndex = random.randint(0, 2)
            refId = random.randint(1, 100)
            value = [
                refId,
                "title" + str(i),
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                "otherinfo" + str(i),
                reason[random.randint(0, 5)],
                random.randint(0, 1),
                type[typeIndex],
                viewurl[typeIndex] + "id=" + str(refId),
                random.randint(1, 100)
            ]
            cur.execute(
                'insert into inform(asso_id,asso_name,c_time,otherinfo,reason,state,type,viewurl,create_userid) values(%s,%s,%s,%s,%s,%s,%s,%s,%s)',
                value)
        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


def resetReason():
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        reason = ["色情", "欺诈", "诋毁侮辱", "广告骚扰", "政治", "非交大校友", "其他"]

        for i in range(1,101):
            value = [
                reason[random.randint(0, 6)],
                str(i)
            ]
            cur.execute('update inform set reason=%s where id=%s', value)
        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


def resetInformUser():
    """重置被举报人id"""

    reload(sys)
    sys.setdefaultencoding('utf8')

    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        sql = "select * from inform"
        cur.execute(sql)
        results = cur.fetchall()
        for row in results:
            id = row[0]
            asso_id = row[1]
            type = row[7]

            if unicode(type) == unicode("话题"):
                sql1 = "select create_userid from topic where id=%s"
                cur.execute(sql1, asso_id)
                create_userid = cur.fetchone()[0]
                sql1 = "update inform set inform_userid=%s where id=%s"
                value = [create_userid, id]
                cur.execute(sql1, value)

            if unicode(type) == unicode("活动"):
                sql1 = "select create_userid from activity where id=%s"
                cur.execute(sql1, asso_id)
                create_userid = cur.fetchone()[0]
                sql1 = "update inform set inform_userid=%s where id=%s"
                value = [create_userid, id]
                cur.execute(sql1, value)

            if unicode(type) == unicode("圈子"):
                sql1 = "select create_userid from circle where id=%s"
                cur.execute(sql1, asso_id)
                create_userid = cur.fetchone()[0]
                sql1 = "update inform set inform_userid=%s where id=%s"
                value = [create_userid, id]
                cur.execute(sql1, value)

        conn.commit()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    initinform()
    resetReason()
    resetInformUser()
