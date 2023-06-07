package com.example.springboot02.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot02.entity.Admin;
import com.example.springboot02.entity.Student;
import com.example.springboot02.mapper.StudentMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @PostMapping("/addStu")
    public Student addStu(@RequestBody Student student){
        studentMapper.insert(student);
        return student;
    }
    @DeleteMapping("/delStu{sno}")
    public int delStu(@PathVariable("sno") String sno){
        return studentMapper.deleteById(sno);
    }
    @GetMapping("/getStu{name}")
    @Operation(summary = "模糊姓名查询")
    public List<Student> getStu(@PathVariable("name") String name){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        return studentMapper.selectList(queryWrapper);
    }
    @GetMapping("/showAllStu")
    public List<Student> showAllStu(){
        return studentMapper.selectList(null);
    }
    @PutMapping("/updateStu{sno}")
    public Student updateStu(@PathVariable("sno") String sno,@RequestBody Student student){
        if(studentMapper.selectById(sno)!=null){
            student.setSno(sno);
            studentMapper.updateById(student);
            return student;
        }
        else
            return null;
    }
    @GetMapping("/findByPage")
    public IPage getStuList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        Page<Student> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");//根据id字段降序排序
        page.addOrder(OrderItem.desc("id"));//添加降序排序条件
        IPage<Student> ipage = studentMapper.selectPage(page,null);
        return ipage;
    }
}
