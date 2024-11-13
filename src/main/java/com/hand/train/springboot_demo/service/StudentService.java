package com.hand.train.springboot_demo.service;

import com.hand.train.springboot_demo.dto.CreateStudentRequest;
import com.hand.train.springboot_demo.dto.StudentResponse;
import com.hand.train.springboot_demo.dto.UpdateStudentRequest;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudent();
    StudentResponse getStudentById(Long id);
    StudentResponse createStudent(CreateStudentRequest request);
    StudentResponse updateStudent(Long id, UpdateStudentRequest request);
    void deleteStudent(Long id);
}
