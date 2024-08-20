package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Application;
import com.example.capstone2.Service.ApplicationService;
import com.example.capstone2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final StudentService studentService;

    @GetMapping("get-all-application")
    public ResponseEntity GetAllApplication() {
        return ResponseEntity.status(200).body(applicationService.getAllApplications());
    }

    @PostMapping("add-application")
    public ResponseEntity AddApplication(@Valid @RequestBody Application application) {
        applicationService.add_application(application);
        return ResponseEntity.status(201).body(new ApiResponse("Application added successfully"));
    }

    @DeleteMapping("delete-application/{applicationid}")
    public ResponseEntity DeleteApplication(@PathVariable int applicationid) {
        applicationService.delete_application(applicationid);
        return ResponseEntity.status(200).body(new ApiResponse("Application deleted successfully"));
    }

    @PutMapping("update-application/{applicationid}")
    public ResponseEntity UpdateApplication(@PathVariable int applicationid,@Valid @RequestBody Application application) {
        applicationService.update_application(applicationid,application);
        return ResponseEntity.status(201).body(new ApiResponse("Application updated successfully"));
    }
}
