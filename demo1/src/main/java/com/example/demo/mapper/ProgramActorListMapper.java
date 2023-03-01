package com.example.demo.mapper;

import com.example.demo.dao.pojo.Actor;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProgramActorListMapper {
    //根据节目id找演员id名单
     List<Integer> getList(Integer id);

     //查询大于等于特定人数条件下的节目id集合
     List<Integer> programListByNumMore(Integer num);
     //查询等于特定人数条件下的节目id集合
     List<Integer> programListByNumEuqal(Integer num);

     //新增演员和节目关系
     int addRelation(@Param("programId") Integer programId,@Param("actorId") Integer actorId);

     //根据节目id删除关系
     int deleteByProgramId(Integer programId);

     //根据演员id查询节目id列表
     List<Integer> getProgramListByActorId(Integer id);
}
