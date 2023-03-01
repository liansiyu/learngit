package com.example.demo.dao.result;

import lombok.Data;

@Data
public class ProgramResult {
    private Integer id;
    private String typeName;
    private String name;
    private String view;
    private String actorList;

    public ProgramResult(Integer id, String typeName, String name, String view, String actorList) {
        this.id = id;
        this.typeName = typeName;
        this.name = name;
        this.view = view;
        this.actorList = actorList;
    }

    public ProgramResult() {
    }

}
