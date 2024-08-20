package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Application;
import com.example.capstone2.Model.Teacher;
import com.example.capstone2.Service.ApplicationService;
import com.example.capstone2.Service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final ApplicationService applicationService;

    @GetMapping("/get-all-teachers")
    public ResponseEntity getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @PostMapping("/add-teacher")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher) {
        teacherService.insertTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added teacher"));
    }

    @PutMapping("/update-teacher/{teacherid}")
    public ResponseEntity updateTeacher(@PathVariable int teacherid,@Valid @RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacherid,teacher);
        return ResponseEntity.status(200).body(new ApiResponse("teacher updated successfully"));
    }

    @DeleteMapping("/delete-teacher/{teacherid}")
    public ResponseEntity deleteTeacher(@PathVariable int teacherid) {
        teacherService.deleteTeacher(teacherid);
        return ResponseEntity.status(200).body(new ApiResponse("teacher deleted successfully"));
    }

    @PostMapping("/publish-application")
    public ResponseEntity publishApplication(@Valid @RequestBody Application application) {
        teacherService.publish_Application(application);
        return ResponseEntity.status(200).body(new ApiResponse("application published successfully"));
    }

    @GetMapping("display-published-applications/{teacherid}")
    public ResponseEntity displayPublishedApplications(@PathVariable int teacherid) {
        return ResponseEntity.status(200).body(teacherService.displayPublishedApplications(teacherid));
    }

    @DeleteMapping("/delete-published-application/{publishedid}")
    public ResponseEntity deletePublishedApplication(@PathVariable int publishedid) {
        teacherService.delete_application(publishedid);
        return ResponseEntity.status(200).body(new ApiResponse("application deleted successfully"));
    }

    @PutMapping("/update-title/{publishedid}")
    public ResponseEntity updateTitle(@PathVariable int publishedid, @RequestBody String title) {
        teacherService.update_title(publishedid,title);
        return ResponseEntity.status(200).body(new ApiResponse("Application title updated successfully"));
    }

    @PutMapping("/update-cost/{publishedid}/{cost}")
    public ResponseEntity updateCost(@PathVariable int publishedid,@PathVariable int cost){
        teacherService.update_cost(publishedid,cost);
        return ResponseEntity.status(200).body(new ApiResponse("Application cost updated successfully"));
    }

    @GetMapping("/get-student-request/{teacherid}")
    public ResponseEntity getStudentRequest(@PathVariable int teacherid) {
        return ResponseEntity.status(200).body(teacherService.show_requests(teacherid));
    }

    @PutMapping("/set-status/{tid}/{reqid}/{status}")
    public ResponseEntity setStatus(@PathVariable int tid,@PathVariable int reqid, @PathVariable String status) {
        teacherService.setStudentStatus(tid,reqid,status);
        return ResponseEntity.status(200).body(new ApiResponse("status set successfully"));
    }

    @GetMapping("/application-comment/{teacherid}")
    public ResponseEntity getApplicationComment(@PathVariable int teacherid) {
        return ResponseEntity.status(200).body(teacherService.application_Comments(teacherid));
    }

    @GetMapping("/display-balance/{teacherid}")
    public ResponseEntity displayBalance(@PathVariable int teacherid){
        return ResponseEntity.status(200).body(teacherService.displayBalance(teacherid));
    }
}
