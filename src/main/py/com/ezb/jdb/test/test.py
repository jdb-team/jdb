#!/usr/bin/python
# -*- coding: utf-8 -*-
# 查看python位数
# Author   : liufeng
# Create   : 2015/8/8 9:35

import platform
import random

if __name__ == '__main__':
    print platform.architecture()
    print "中文"
    print round(random.random() * 360,2)