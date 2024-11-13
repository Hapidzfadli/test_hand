package com.hand.train.springboot_demo;

import com.hand.train.springboot_demo.dto.CreateStudentRequest;
import com.hand.train.springboot_demo.dto.StudentResponse;
import com.hand.train.springboot_demo.dto.UpdateStudentRequest;
import com.hand.train.springboot_demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringbootDemoApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	void testGetAllStudents() {
		List<StudentResponse> students = studentService.getAllStudent();
		assertNotNull(students, "Students should not be null");

		System.out.println("Total students found: " + students.size());
		for (StudentResponse student : students) {
			System.out.println("Student: " + student.getSId() + " - " + student.getSName());
		}
	}

	@Test
	void testGetStudentById() {
		Long studentId = 1L;
		StudentResponse student = studentService.getStudentById(studentId);

		assertNotNull(student, "Student should be not null");
		System.out.println("Found student: " + student.getSId() + " - " + student.getSName() +
				" - " + student.getSBrith() + " - " + student.getSSex());
	}

	@Test
	void testCreateStudent() {
		CreateStudentRequest newStudent = CreateStudentRequest.builder()
				.sId(10L)
				.sName("Test Student")
				.sBrith("2000-01-01")
				.sSex("M")
				.build();

		assertNotNull(newStudent, "New student should not be null");

		StudentResponse createdStudent = studentService.createStudent(newStudent);
		System.out.println("Created student: " + createdStudent.getSId() + " - " + createdStudent.getSName());
	}

	@Test
	void testUpdateStudent() {
		Long studentId = 1L;
		UpdateStudentRequest updateRequest = UpdateStudentRequest.builder()
				.sName("Updated Name")
				.sBrith("2000-01-01")
				.sSex("M")
				.build();

		StudentResponse updatedStudent = studentService.updateStudent(studentId, updateRequest);
		System.out.println("Updated student: " + updatedStudent.getSId() + " - " + updatedStudent.getSName());
	}

	@Test
	void testDeleteStudent() {
		Long studentId = 10L; // Sesuaikan dengan ID yang ada di database
		studentService.deleteStudent(studentId);
		System.out.println("Deleted student with ID: " + studentId);
	}

	@Test
	void testFullCrudFlow() {
		// 1. Create
		CreateStudentRequest newStudent = CreateStudentRequest.builder()
				.sId(20L)
				.sName("CRUD Test Student")
				.sBrith("2000-01-01")
				.sSex("F")
				.build();

		StudentResponse createdStudent = studentService.createStudent(newStudent);
		System.out.println("1. Created student: " + createdStudent.getSId() + " - " + createdStudent.getSName());

		// 2. Read
		StudentResponse foundStudent = studentService.getStudentById(createdStudent.getSId());
		System.out.println("2. Found student: " + foundStudent.getSId() + " - " + foundStudent.getSName());

		// 3. Update
		UpdateStudentRequest updateRequest = UpdateStudentRequest.builder()
				.sName("Updated CRUD Test")
				.sBrith("2000-01-01")
				.sSex("F")
				.build();

		StudentResponse updatedStudent = studentService.updateStudent(createdStudent.getSId(), updateRequest);
		System.out.println("3. Updated student: " + updatedStudent.getSId() + " - " + updatedStudent.getSName());

		// 4. Delete
		studentService.deleteStudent(createdStudent.getSId());
		System.out.println("4. Deleted student with ID: " + createdStudent.getSId());
	}
}