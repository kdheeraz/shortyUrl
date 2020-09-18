package com.example.demo;

public class DBModelWithId {
    private String title;

    

    private String id;

    private String description;
    private String group;

    private String url;
    private int expTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getExpTime() {
        return expTime;
    }

    public void setExpTime(int expTime) {
        this.expTime = expTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


   
}
