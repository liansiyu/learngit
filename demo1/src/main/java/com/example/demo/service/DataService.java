package com.example.demo.service;

import com.example.demo.dao.param.ProgramParam;
import com.example.demo.dao.pojo.Program;
import com.example.demo.dao.result.ProgramResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface DataService {
    //获取全部节目信息
    List<ProgramResult> getProgramList(Integer pageNum);
    //根据特定条件查询节目信息
    List<ProgramResult> search(Integer type, Integer num, String keyword,Integer pageNum,HttpSession session);
    //根据节目id查询演员名单
    String getActorList(Integer id);
    //更新节目单信息
    void updateProgram(Integer id,
                       Integer type,
                       String name,
                       String actorList,
                       String view);
    //删除节目信息
    void deleteProgram(Integer id);
    //添加节目信息
    void addProgram(Integer type,
                    String name,
                    String actorList,
                    String view);
    //获取节目条数
    int getProgramCount();

    //查询特定条件下的节目总条数
    List<Integer> searchCount(Integer type, Integer num, String keyword);

   // 根据id获取节目的信息
    ProgramResult getOne(Integer id);

    //删除节目相关的演员信息和关系
    void deleteActorRelation(Integer id);

    //确认节目名字
    Program checkName(String name);
}
