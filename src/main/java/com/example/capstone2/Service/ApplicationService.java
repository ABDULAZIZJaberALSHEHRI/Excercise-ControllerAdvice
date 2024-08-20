package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Application;
import com.example.capstone2.Model.Teacher;
import com.example.capstone2.Repository.ApplicationRespository;
import com.example.capstone2.Repository.StudentRepository;
import com.example.capstone2.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final ApplicationRespository applicationRespository;
    private final TeacherRepository teacherRepository;

    public List<Application> getAllApplications() {
        return applicationRespository.findAll();
    }

    public void add_application(Application application) {
        Teacher t = teacherRepository.findTeacherById(application.getTeacher_id());
        if (t == null) {
            throw new ApiException("Teacher Not Found");
        }
        applicationRespository.save(application);
    }

    public void delete_application(int application_id) {
        Application a = applicationRespository.findByApplicationId(application_id);
        if(a == null) {
            throw new ApiException("Application not found");
        }
        applicationRespository.delete(a);
    }

    public void update_application(int application_id, Application application) {
        Teacher t = teacherRepository.findTeacherById(application.getTeacher_id());
        if (t == null) {
            throw new ApiException("Teacher Not Found");
        }

        Application a = applicationRespository.findByApplicationId(application_id);
        if(a == null) {
            throw new ApiException("Application not found");
        }

        a.setApplication_Title(application.getApplication_Title());
        a.setApplication_Description(application.getApplication_Description());
        a.setTeacher_id(application.getTeacher_id());
        a.setTarget_Student(application.getTarget_Student());
        a.setCourse_Name(application.getCourse_Name());
        a.setCourse_Duration(application.getCourse_Duration());
        a.setSession_Type(application.getSession_Type());
        a.setSession_Location(a.getSession_Location());
        a.setStart_date(application.getStart_date());
        a.setEnd_date(application.getEnd_date());
        a.setStart_time(application.getStart_time());
        a.setEnd_time(application.getEnd_time());
        a.setCost(application.getCost());
        applicationRespository.save(a);

    }
}
