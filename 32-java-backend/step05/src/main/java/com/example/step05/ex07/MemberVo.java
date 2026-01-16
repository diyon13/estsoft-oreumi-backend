package com.example.step05.ex07;

import java.sql.Date;

/**
 * members 테이블의 레코드(record; row)를 표현하는 VO(Value Object) 클래스
 */
public class MemberVo {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Date createdAt;

    public MemberVo(int id, String username, String password, String name, String email, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public MemberVo() {
        this(0, null, null, null, null, null);
    }

    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public Date getCreatedAt() {return createdAt;}
}