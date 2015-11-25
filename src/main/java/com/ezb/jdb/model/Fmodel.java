package com.ezb.jdb.model;

/**
 * author : liufeng
 * create time: 2015/8/5 14:01.
 */
public class Fmodel {

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public int hashCode(){
        return super.hashCode();
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
