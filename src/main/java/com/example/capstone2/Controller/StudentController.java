package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Model.StudentRequest;
import com.example.capstone2.Repository.StudentRequestRepository;
import com.example.capstone2.Service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all-students")
    public ResponseEntity GetAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add-student")
    public ResponseEntity AddStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update-student/{studentid}")
    public ResponseEntity UpdateStudent(@PathVariable int studentid,@Valid @RequestBody Student student) {
        studentService.updateStudent(studentid, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete-student/{studentid}")
    public ResponseEntity DeleteStudent(@PathVariable int studentid) {
        studentService.deleteStudent(studentid);
        return ResponseEntity.status(201).body(new ApiResponse("Student deleted successfully")); 
    }

    @GetMapping("/display-all-applications")
    public ResponseEntity GetAllApplications() {
        return ResponseEntity.status(200).body(studentService.displayAllApplications());
    }

    @PostMapping("/add-request-application/{applicationid}/{studentid}")
    public ResponseEntity AddRequestApplication(@PathVariable int applicationid, @PathVariable int studentid) {
        studentService.addRequestApplication(applicationid, studentid);
        return ResponseEntity.status(201).body(new ApiResponse("Request application completed successfully"));
    }

    @DeleteMapping("/delete-request/{requestid}")
    public ResponseEntity DeleteRequest(@PathVariable int requestid) {
        studentService.deleteRequestApplication(requestid);
        return ResponseEntity.status(201).body(new ApiResponse("Request deleted successfully"));
    }

    @PutMapping("/rate-teacher/{tid}/{sid}/{rate}")
    public ResponseEntity rateTeacher(@PathVariable int tid, @PathVariable int sid,@Max(5) @Min (0) @PathVariable double rate) {
        studentService.rate_teacher(tid, sid, rate);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher rates successfully"));
    }

    @PostMapping("add-comment/{applicationid}/{studentid}")
    public ResponseEntity AddComment(@PathVariable int applicationid, @PathVariable int studentid, @RequestBody String comment) {
        studentService.addComment(applicationid, studentid, comment);
        return ResponseEntity.status(201).body(new ApiResponse("Comment added successfully"));
    }

    @GetMapping("/show-comment-by-student-id/{studentid}")
    public ResponseEntity ShowCommentByStudentId(@PathVariable int studentid) {

        return ResponseEntity.status(200).body(studentService.displayComments(studentid));
    }

    @DeleteMapping("delete-comment/{commentid}")
    public ResponseEntity DeleteComment(@PathVariable int commentid) {
        studentService.deleteComment(commentid);
        return ResponseEntity.status(201).body(new ApiResponse("Comment deleted successfully"));
    }

    @GetMapping("search-by-course")
    public ResponseEntity searchByCourse(@RequestBody String course){
        return ResponseEntity.status(200).body(studentService.displayApplicationsByCourse(course));
    }
}
