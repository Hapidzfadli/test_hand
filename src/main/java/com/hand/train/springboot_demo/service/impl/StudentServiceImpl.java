package com.hand.train.springboot_demo.service.impl;

import com.hand.train.springboot_demo.dto.CreateStudentRequest;
import com.hand.train.springboot_demo.dto.StudentResponse;
import com.hand.train.springboot_demo.dto.UpdateStudentRequest;
import com.hand.train.springboot_demo.po.Student;
import com.hand.train.springboot_demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StudentServiceImpl extends SqlSessionDaoSupport implements StudentService {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public List<StudentResponse> getAllStudent() {
        SqlSession sqlSession = this.getSqlSession();
        List<Student> students = sqlSession.selectList("com.hand.train.springboot_demo.mapper.StudentMapper.findAll");
        return students.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        SqlSession sqlSession = this.getSqlSession();
        Student student = sqlSession.selectOne(
                "com.hand.train.springboot_demo.mapper.StudentMapper.findById", id);
        if(student == null) {
            throw new RuntimeException("Student not found");
        }
        return mapToResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse createStudent(CreateStudentRequest request) {
        SqlSession sqlSession = this.getSqlSession();

        Student student = Student.builder()
                .sId(request.getSId())
                .sName(request.getSName())
                .sBrith(request.getSBrith())
                .sSex(request.getSSex())
                .build();

        sqlSession.insert("com.hand.train.springboot_demo.mapper.StudentMapper.create", student);
        return mapToResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Long id, UpdateStudentRequest request) {
        SqlSession sqlSession = this.getSqlSession();
        Student existingStudent = sqlSession.selectOne(
                "com.hand.train.springboot_demo.mapper.StudentMapper.findById", id);

        if(existingStudent == null) {
            throw new RuntimeException("Student not found");
        }

        Student student = Student.builder()
                .sId(id)
                .sName(request.getSName())
                .sBrith(request.getSBrith())
                .sSex(request.getSSex())
                .build();

        sqlSession.update("com.hand.train.springboot_demo.mapper.StudentMapper.update", student);
        return mapToResponse(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        SqlSession sqlSession = this.getSqlSession();
        Student student = sqlSession.selectOne(
                "com.hand.train.springboot_demo.mapper.StudentMapper.findById", id);

        if(student == null) {
            throw new RuntimeException("Student not found");
        }
        sqlSession.delete("com.hand.train.springboot_demo.mapper.StudentMapper.delete", id);
    }

    private StudentResponse mapToResponse(Student student) {
        return StudentResponse.builder()
                .sId(student.getSId())
                .sName(student.getSName())
                .sBrith(student.getSBrith())
                .sSex(student.getSSex())
                .build();
    }
}