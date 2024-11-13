package com.hand.train.springboot_demo.controller;

import com.hand.train.springboot_demo.dto.CreateStudentRequest;
import com.hand.train.springboot_demo.dto.StudentResponse;
import com.hand.train.springboot_demo.dto.UpdateStudentRequest;
import com.hand.train.springboot_demo.service.StudentService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @ApiOperation("Get all student")
    public ResponseEntity<List<StudentResponse>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get student by id")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    @ApiOperation("Create new student")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update student by id")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid UpdateStudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete student by id")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
