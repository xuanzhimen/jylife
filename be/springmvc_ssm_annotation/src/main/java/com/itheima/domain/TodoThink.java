package com.itheima.domain;

import java.io.Serializable;

/**
 * (TodoThink)实体类
 *
 * @author makejava
 * @since 2020-07-07 00:52:06
 */
public class TodoThink implements Serializable {
    private static final long serialVersionUID = 500495088333337L;
    
    private Integer id;
    
    private Long makeTime;
    
    private String content;
    
    private Integer uid;

    public TodoThink() {
    }

    public TodoThink(Integer id, Long makeTime, String content, Integer uid) {
        this.id = id;
        this.makeTime = makeTime;
        this.content = content;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}