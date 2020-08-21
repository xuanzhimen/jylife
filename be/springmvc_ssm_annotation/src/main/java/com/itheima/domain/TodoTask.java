package com.itheima.domain;

import java.io.Serializable;

/**
 * (TodoTask)实体类
 *
 * @author makejava
 * @since 2020-07-06 17:21:36
 */
public class TodoTask implements Serializable {
    private static final long serialVersionUID = 769856150131750202L;
    
    private Integer id;
    /**
    * 任务名称
    */
    private String name;
    /**
    * 期望完成的日期
    */
    private Long expectedTime;
    /**
    * 实际完成的日期
    */
    private Long actualTime;
    /**
    * 创建时间
    */
    private Long makeTime;
    
    private Long operaTime;
    
    private Integer uid;

    public TodoTask() {
    }

    public TodoTask(Integer id, String name, Long expectedTime, Long actualTime, Long makeTime, Long operaTime, Integer uid) {
        this.id = id;
        this.name = name;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
        this.makeTime = makeTime;
        this.operaTime = operaTime;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "name='" + name + '\'' +
                ", expectedTime=" + expectedTime +
                ", actualTime=" + actualTime +
                ", makeTime=" + makeTime +
                ", operaTime=" + operaTime +
                ", uid=" + uid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Long expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Long getActualTime() {
        return actualTime;
    }

    public void setActualTime(Long actualTime) {
        this.actualTime = actualTime;
    }

    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
    }

    public Long getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(Long operaTime) {
        this.operaTime = operaTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}