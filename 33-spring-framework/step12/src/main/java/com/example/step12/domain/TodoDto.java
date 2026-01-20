package com.example.step12.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDto {
    private String title;
    private Date dueDate;
}
