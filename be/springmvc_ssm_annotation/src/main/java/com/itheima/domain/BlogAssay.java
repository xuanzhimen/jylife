package com.itheima.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * (BlogAssay)实体类
 *
 * @author makejava
 * @since 2020-07-03 07:53:31
 */
public class BlogAssay implements Serializable {
    private static final long serialVersionUID = 226553824946203254L;
    
    private Integer id;
    
    private String title;
    
    private String content;
    
    private Integer cid;
    
    private Date maketime;
    
    private Date operatime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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