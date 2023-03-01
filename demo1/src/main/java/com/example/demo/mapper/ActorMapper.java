package com.example.demo.mapper;

import com.example.demo.dao.pojo.Actor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActorMapper {
    //根据特定演员id查询特定演员信息
    Actor search (Integer id);

    //根据演员名称查询特定演员信息
    Actor getActorByName(String name);

    //根据演员名称添加演员信息
    int addActorByName(String name);

    //根据演员id删除演员信息
    int deleteActorById(Integer id);
}
