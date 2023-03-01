package com.example.demo.service.impl;

import com.example.demo.dao.param.ProgramParam;
import com.example.demo.dao.pojo.Program;
import com.example.demo.dao.result.ProgramResult;
import com.example.demo.mapper.ActorMapper;
import com.example.demo.mapper.ProgramActorListMapper;
import com.example.demo.mapper.ProgramMapper;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {
    @Resource
    ProgramMapper programMapper;
    @Resource
    ActorMapper actorMapper;
    @Resource
    ProgramActorListMapper programActorListMapper;
    @Resource
    TypeMapper typeMapper;

    @Override
    public List<ProgramResult> getProgramList(Integer pageNum) {
        List<ProgramResult> programResults = new ArrayList<>();
        Integer  i = (pageNum-1)*4;
        List<Program> programs = programMapper.selectAll(i);
        for(Program program:programs){
           ProgramResult programResult = new ProgramResult();
           programResult.setId(program.getId());
           programResult.setTypeName(typeMapper.getType(program.getTypeId()).getName());
           programResult.setName(program.getName());
           programResult.setView(program.getView());
           programResult.setActorList(getActorList(program.getId()));
           programResults.add(programResult);
        }
        return programResults;
    }

    @Override
    public List<Integer> searchCount(Integer type, Integer num, String keyword) {
        List<Integer> list1;
        List<Integer> programsIdList = new ArrayList<>();
        if(type==0){
            list1 = programMapper.searchByOne(keyword);
        }else{
            list1 = programMapper.searchByTwo(type, keyword);
        }
        if(num==0){
            programsIdList = list1;
        }else if(num==3){
            List<Integer> list2 = programActorListMapper.programListByNumMore(num);
            for(Integer a:list1){
                if(list2.contains(a)){
                    programsIdList.add(a);
                }
            }
        }else{
            List<Integer> list2 = programActorListMapper.programListByNumEuqal(num);
            for(Integer a:list1){
                if(list2.contains(a)){
                    programsIdList.add(a);
                }
            }
        }
        return programsIdList;
    }

    @Override
    public ProgramResult getOne(Integer id) {
        Program program = programMapper.load(id);

        return new ProgramResult(program.getId(),
                                typeMapper.getType(program.getTypeId()).getName(),
                                program.getName(),
                                program.getView(),getActorList(id));
    }

    @Override
    public List<ProgramResult> search(Integer type, Integer num, String keyword, Integer pageNum, HttpSession session) {
        List<ProgramResult> programResults = new ArrayList<>();
        List<Program> programs = new ArrayList<>();
        pageNum = (pageNum-1)*4;
        List<Integer> programsIdList = searchCount(type,num,keyword);
        int pageCount = (programsIdList.size()+4-1)/4;
        session.setAttribute("pageCount",pageCount);

        if(pageNum+4<=programsIdList.size()){
            programsIdList = programsIdList.subList(pageNum,pageNum+4);
        }else{
            programsIdList = programsIdList.subList(pageNum,programsIdList.size());
        }

        for(Integer a:programsIdList){
             programs.add( programMapper.load(a));
        }

        for(Program program:programs){
            programResults.add(new ProgramResult(program.getId(),
                    typeMapper.getType(program.getTypeId()).getName(),
                    program.getName(),program.getView(),getActorList(program.getId()))
            );
        }

        return programResults;

    }

    //根据节目id寻找演员名单
    public String getActorList(Integer id){
        String s = "";
        List<Integer> list = programActorListMapper.getList(id);
        for(Integer i:list){
            s = s + actorMapper.search(i).getName()+" ";
        }
        return s;
    }

    public void updateProgram(Integer id,
                              Integer type,
                              String name,
                              String actorList,
                              String view){

        programMapper.updateById(id,name,type,view);
        deleteActorRelation(id);
        if(actorList.equals("")){
            return;
        }
        String[] actors = actorList.split(" ");
        for(String s:actors){
            if(actorMapper.getActorByName(s)==null){
                actorMapper.addActorByName(s);
                programActorListMapper.addRelation(id,actorMapper.getActorByName(s).getId());
            }else{
                programActorListMapper.addRelation(id,actorMapper.getActorByName(s).getId());
            }
        }

    }

     public void deleteActorRelation(Integer id){
         List<Integer> actorList = programActorListMapper.getList(id);
         programActorListMapper.deleteByProgramId(id);
         for(Integer i:actorList){
             if(programActorListMapper.getProgramListByActorId(i).size()==0){
                 actorMapper.deleteActorById(i);
             }
         }
     }

    @Override
    public Program checkName(String name) {
        return programMapper.getProgramByName(name);
    }

    @Override
    public void deleteProgram(Integer id) {
       deleteActorRelation(id);
        programMapper.deleteById(id);
    }

    @Override
    public void addProgram(Integer type,
                           String name,
                           String actorList,
                           String view) {
        Program program = new Program(name,type,view);
        programMapper.add(program);
        if(actorList.equals("")){
            return;
        }
        String[] actors = actorList.split(" ");
        for(String s:actors){
            if(actorMapper.getActorByName(s)==null){
                actorMapper.addActorByName(s);
                programActorListMapper.addRelation(program.getId(),actorMapper.getActorByName(s).getId());
            }else{
                programActorListMapper.addRelation(program.getId(),actorMapper.getActorByName(s).getId());
            }
        }
    }

    @Override
    public int getProgramCount() {
        return programMapper.getCount();
    }




}
