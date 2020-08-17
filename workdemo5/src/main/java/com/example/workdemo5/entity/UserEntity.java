package com.example.workdemo5.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

    private static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(length = 20)
    private String userName;
    @Column(length = 20)
    private String password;
    //用户类别（0：管理员，1：用户）
    @Column
    private Integer userType;
    @Column(length = 20)
    private String sex;
    @Column
    private Integer tel;
    @Column(length = 20)
    private String email;
    @Column(length = 20)
    private String address;
    @Column(length = 20)
    private String createTime;
    @Column(length = 20)
    private String modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", sex='" + sex + '\'' +
                ", tel=" + tel +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
