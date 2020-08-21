package com.itheima.domain;

public class MindMapNode {
    private static final long serialVersionUID = -2711486149895463L;
    private String id;
    private String topic;
    private String pid;
    private String style;
    private String tags;
    private String icons;
    private int root;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
}
