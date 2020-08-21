package com.itheima.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (BlogCategory)实体类
 *
 * @author makejava
 * @since 2020-07-04 02:00:09
 */
public class BlogCategory implements Serializable {
    private static final long serialVersionUID = -27114873901195463L;
    
    private Integer id;
    
    private String name;
    
    private Integer pid;
    
    private Integer uid;
    
    private Date maketime;

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    private Date operatime;

    private Integer delflag;


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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getMaketime() {
        return maketime;
    }

    public void setMaketime(Date maketime) {
        this.maketime = maketime;
    }

    public Date getOperatime() {
        return operatime;
    }

    public void setOperatime(Date operatime) {
        this.operatime = operatime;
    }

}