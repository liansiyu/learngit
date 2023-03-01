package com.example.demo.mapper;

import com.example.demo.dao.param.ProgramParam;
import com.example.demo.dao.pojo.Program;
import com.example.demo.dao.result.ProgramResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgramMapper {
    //查询全部节目信息
    List<Program> selectAll(Integer pageNum);
    //根据类型，关键字找节目信息
    List<Integer> searchByTwo(@Param("type") Integer type,
                               @Param("keyword") String keyword);
    //根据关键字找节目信息
    List<Integer> searchByOne(@Param("keyword") String keyword);

    //查找特定节目信息
    Program load(Integer id);

    //根据id更新节目信息
    int updateById(@Param("id")Integer id,
                   @Param("name")String name,
                   @Param("type")Integer type,
                   @Param("view")String view);
    //根据id删除节目信息
    int deleteById(Integer id);
    //添加节目信息
    int add(Program program);

    //查找节目总条数
    int getCount();

    //根据节目名字找节目
    Program getProgramByName(String name);


}
