#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/11/19 15:05
import hashlib


def md5(plaintext):
    m2 = hashlib.md5()
    m2.update(plaintext)
    return m2.hexdigest()


if __name__ == '__main__':
    print md5("pass1")
