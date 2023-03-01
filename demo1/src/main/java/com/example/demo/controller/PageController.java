package com.example.demo.controller;

import com.example.demo.dao.result.ProgramResult;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    DataService dataService;

    @RequestMapping("/index")
    public String index(){
        return "SP-IMS";
    }

    @RequestMapping("/edit")
    public String edit(){
        return "edit";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

}
