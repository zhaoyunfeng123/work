package com.example.workdemo5.entity;

import java.io.Serializable;

public class MailEntity implements Serializable {

    private static Long serialVersionUID = 63254231L;

    //邮件地址
    private String to;
    //邮件标头
    private String title;
    //邮件正文
    private String content;

    @Override
    public String toString() {
        return "MailEntity{" +
                "to='" + to + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
}
