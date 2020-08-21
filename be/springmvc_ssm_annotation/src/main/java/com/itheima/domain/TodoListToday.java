package com.itheima.domain;

import java.io.Serializable;

/**
 * (TodoListToday)实体类
 *
 * @author makejava
 * @since 2020-07-06 17:21:36
 */
public class TodoListToday implements Serializable {
    private static final long serialVersionUID = 352224122786043096L;
    
    private Integer id;
    /**
    * 计划内容
    */
    private String name;
    
    private Long expectedTime;
    
    private Long makeTime;
    
    private Long operaTime;
    
    private Object beginTime;
    
    private Object endTime;
    
    private Object isOutPlan;
    
    private Integer uid;

    private Long actualTime;

    private Integer isRunning;

    public TodoListToday(Integer id, String name, Long expectedTime, Long makeTime, Long operaTime, Object beginTime, Object endTime, Object isOutPlan, Integer uid, Long actualTime, Integer isRunning) {
        this.id = id;
        this.name = name;
        this.expectedTime = expectedTime;
        this.makeTime = makeTime;
        this.operaTime = operaTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isOutPlan = isOutPlan;
        this.uid = uid;
        this.actualTime = actualTime;
        this.isRunning = isRunning;
    }

    public TodoListToday() {
    }

    public Integer getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Integer isRunning) {
        this.isRunning = isRunning;
    }

    public Long getActualTime() {
        return actualTime;
    }

    public void setActualTime(Long actualTime) {
        this.actualTime = actualTime;
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

    public Object getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Object beginTime) {
        this.beginTime = beginTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Object getIsOutPlan() {
        return isOutPlan;
    }

    public void setIsOutPlan(Object isOutPlan) {
        this.isOutPlan = isOutPlan;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}