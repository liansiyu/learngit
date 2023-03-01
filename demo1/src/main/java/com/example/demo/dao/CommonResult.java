package com.example.demo.dao;

import com.example.demo.dao.result.ProgramResult;
import lombok.Data;

import java.util.List;

@Data
public class CommonResult {
    private String code;
    private String message;
    private List<ProgramResult> data;
}
