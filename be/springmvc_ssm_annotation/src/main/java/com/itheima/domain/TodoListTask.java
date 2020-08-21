package com.itheima.domain;

import java.io.Serializable;

/**
 * (TodoListTask)实体类
 *
 * @author makejava
 * @since 2020-07-06 17:21:34
 */
public class TodoListTask implements Serializable {
    private static final long serialVersionUID = 616929891768997045L;
    
    private Integer tid;
    
    private Integer lid;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

}