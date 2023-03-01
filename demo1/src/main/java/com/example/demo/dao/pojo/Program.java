package com.example.demo.dao.pojo;

import lombok.Data;

@Data
public class Program {
    private Integer id;
    private Integer typeId;
    private String name;
    private String view;

    public Program(String name, Integer type, String view) {
        this.name = name;
        this.typeId = type;
        this.view = view;
    }
}
