package com.example.demo.controller;

import com.example.demo.dao.CommonResult;
import com.example.demo.dao.param.ProgramParam;
import com.example.demo.dao.pojo.Program;
import com.example.demo.dao.result.ProgramResult;
import com.example.demo.service.DataService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class DataController {
    @Autowired
    DataService dataService;


    @GetMapping("/get/search")
    public void search(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam(required = false,defaultValue = "0",value = "typeId")  Integer typeId,
                         @RequestParam(required = false,defaultValue = "0",value = "num")  Integer num,
                         @RequestParam(required = false,defaultValue = "",value = "keyword") String keyword,
                         HttpSession session,
                         HttpServletResponse response) throws IOException {
        System.out.println(""+pageNum+","+typeId+","+num+","+keyword);
        session.setAttribute("pageNum",pageNum);
        session.setAttribute("typeId",typeId);
        session.setAttribute("num",num);
        session.setAttribute("keyword",keyword);
        if(typeId==0&&num==0&&keyword.equals("")){
            List<ProgramResult> list = dataService.getProgramList(pageNum);
            session.setAttribute("data",list);
            int Count = dataService.getProgramCount();
            int pageCount = (Count+4-1)/4;
            session.setAttribute("pageCount",pageCount);
        }else {
            List<ProgramResult> list = dataService.search(typeId,num,keyword,pageNum,session);
            session.setAttribute("data",list);
        }
        response.sendRedirect("/index");
    }

    @GetMapping("/get/update")
    public void update(@RequestParam(required = true,value = "id")Integer id,
                       @RequestParam(required = true,value = "type")Integer type,
                       @RequestParam(required = true,value = "name")String name,
                       @RequestParam(required = false,defaultValue = "",value = "actorList")String actorList,
                       @RequestParam(required = false,defaultValue = "",value = "view")String view,
                          HttpServletResponse response) throws IOException {

        dataService.updateProgram(id,type,name,actorList,view);
        response.sendRedirect("/get/search");
    }

    @GetMapping("/get/delete")
    public void delete(@RequestParam Integer id,HttpServletResponse response) throws IOException {

        dataService.deleteProgram(id);
        response.sendRedirect("/get/search");
    }

    @GetMapping("/get/add")
    public void add(
                            @RequestParam(required = true,value = "type")Integer type,
                            @RequestParam(required = true,value = "name")String name,
                            @RequestParam(required = false,defaultValue = "",value = "actorList")String actorList,
                            @RequestParam(required = false,defaultValue = "",value = "view")String view,
                            HttpServletResponse response) throws IOException {

        dataService.addProgram(type,name,actorList,view);
        response.sendRedirect("/get/search");
    }

    @GetMapping("/get/one")
    public void getOne(@RequestParam Integer id,HttpSession session,HttpServletResponse response) throws IOException {
        session.setAttribute("editprogram",dataService.getOne(id));
        response.sendRedirect("/edit");
    }

    @GetMapping("/get/check")
    public void check(@RequestParam String name,HttpServletResponse response) throws IOException {
        Program program = dataService.checkName(name);
        String s;
        if(program!=null){
            s = "{'name':'1'}";
            // return "ajax:1";
        }else{
            s = "{'name':'0'}";
            // return "ajax:0";
        }
        PrintWriter out = response.getWriter();
        out.print(s);
        out.flush();
    }

}
