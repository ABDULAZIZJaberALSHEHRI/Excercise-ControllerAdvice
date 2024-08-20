package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TeacherService {
    private final StudentRequestRepository studentRequestRepository;
    private final TeacherRepository teacherRepository;
    private final ApplicationRespository applicationRespository;
    private final Published_ApplicationsRepository published_applicationsRepository;
    private final FinancialReturnRepository financialReturnRepository;
    private final Teacher_BalanceRepository teacher_BalanceRepository;
    private final CommentRepository commentRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void insertTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(int teacher_id) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        Application application = applicationRespository.findByTeacherId(teacher_id);
        if (application != null) {
            applicationRespository.delete(application);

            Published_Applications publishedApplications = published_applicationsRepository.findByPublished_Applications_id(application.getTeacher_id());
            published_applicationsRepository.delete(publishedApplications);

            StudentRequest s;
            s = studentRequestRepository.findByApplication_id(publishedApplications.getApplication_id());
            if (s!=null){
                studentRequestRepository.delete(s);
            }
        }

        teacherRepository.delete(teacher);



    }

    public void updateTeacher(int teacher_id, Teacher teacher) {
        Teacher teacher1 = teacherRepository.findTeacherById(teacher_id);
        if (teacher1 == null) {
            throw new ApiException("Teacher not found");
        }
        teacher1.setFName(teacher.getFName());
        teacher1.setLName(teacher.getLName());
        teacher1.setAge(teacher.getAge());
        teacher1.setCity(teacher.getCity());
        teacher1.setCareer(teacher.getCareer());
        teacher1.setPhoneNumber(teacher.getPhoneNumber());
        teacherRepository.save(teacher1);
    }

    //teacher publish application or offer
    public void publish_Application(Application application){
        Teacher t = teacherRepository.findTeacherById(application.getTeacher_id());
        if (t == null) {
            throw new ApiException("Teacher Not Found");
        }

        Application application1=applicationRespository.findByTeacherId(t.getTeacher_id());
        if (!applicationRespository.findAll().isEmpty()){
            if (application.getTeacher_id() == application1.getTeacher_id()) {
                throw new ApiException("Teacher is already published");
            }
        }


        //check if teacher career match course name or not, if didn't match server will throw exception
        if (!(Objects.equals(t.getCareer(), application.getCourse_Name()))){
            throw new ApiException("Teacher Career Not match offer or application course name");
        }
        applicationRespository.save(application);

        Published_Applications publishedApplications = new Published_Applications();
        publishedApplications.setApplication_id(application.getApplication_id());
        publishedApplications.setTeacher_id(application.getTeacher_id());
        publishedApplications.setApplication_title(application.getApplication_Title());
        published_applicationsRepository.save(publishedApplications);
    }

    //teacher can display it's published application or offers
    public List<Published_Applications> displayPublishedApplications(int teacher_id){
        List<Published_Applications> t = published_applicationsRepository.findByTeacher_id(teacher_id);
        if (t == null) {
            throw new ApiException("Published Applications Not Found");
        }
        return t;
    }

    //teacher can delete its offer
    public void delete_application(int published_application_id) {
        Application a;
        StudentRequest s;
        Published_Applications p = published_applicationsRepository.findByPublished_Applications_id(published_application_id);

        if (p == null) {
            throw new ApiException("Published Application not found");
        }
        a = applicationRespository.findByApplicationId(p.getApplication_id());
        s = studentRequestRepository.findByApplication_id(p.getApplication_id());
        if (s!=null){
            studentRequestRepository.delete(s);
        }

        published_applicationsRepository.delete(p);
        applicationRespository.delete(a);
    }

    //teacher can update its application title
    public void update_title(int published_application_id, String title) {
        Published_Applications p = published_applicationsRepository.findByPublished_Applications_id(published_application_id);
        if (p == null) {
            throw new ApiException("Published Application not found");
        }
        Application a = applicationRespository.findByApplicationId(p.getApplication_id());
        a.setApplication_Title(title);
        applicationRespository.save(a);
    }

    //teacher can update offer cost
    public void update_cost(int published_application_id, int cost) {
        Published_Applications p = published_applicationsRepository.findByPublished_Applications_id(published_application_id);
        if (p == null) {
            throw new ApiException("Published Application not found");
        }
        Application a = applicationRespository.findByApplicationId(p.getApplication_id());
        a.setCost(cost);
        applicationRespository.save(a);
    }

    //teacher can see students requests for its offer or application
    public List<StudentRequest> show_requests(int teacher_id) {

        Teacher t = teacherRepository.findTeacherById(teacher_id);
        if (t == null) {
            throw new ApiException("Teacher Not Found");
        }
        Application a = applicationRespository.findByTeacherId(teacher_id);
        if (a == null) {
            throw new ApiException("Teacher didn't publish Application");
        }
        List<StudentRequest> s = studentRequestRepository.findByApplicationId(a.getApplication_id());
        if (s == null) {
            throw new ApiException("Student Requests Not Found");
        }
        return s;

    }

    //teacher set student application request to approved or not approved
    public void setStudentStatus(int teacher_id,int student_Request, String status) {

        Teacher_Balance tb = new Teacher_Balance();

        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if (teacher == null) {
            throw new ApiException("Teacher Not Found");
        }
        StudentRequest sr = studentRequestRepository.findByStudentRequestId(student_Request);
        if (sr == null) {
            throw new ApiException("Student Requests Not Found");
        }
        Application a = applicationRespository.findByApplicationId(sr.getApplication_id());
        if (a.getTeacher_id() != teacher_id) {
            throw new ApiException("chosen request does not belong to teacher");
        }
        sr.setRequest_status(status);

        //if request status approved the teacher balance will add the course cost
        if (sr.getRequest_status().equalsIgnoreCase("approved")) {

            //0.15 it the percentage that the platform will take from teacher balance and add it to financial returns
            teacher.setBalance(teacher.getBalance()+(a.getCost()-(a.getCost()*0.15)));

            tb.setTeacher_id(teacher_id);
            tb.setStudent_Request_id(student_Request);
            tb.setProcess_value(a.getCost());
            tb.setProcess_value_after_percentage(a.getCost()-(a.getCost()*0.15));
            tb.setTotal_balance(teacher.getBalance());
            teacher_BalanceRepository.save(tb);
        }
        studentRequestRepository.save(sr);
        teacherRepository.save(teacher);

        FinancialReturn f = new FinancialReturn();
        f.setStudent_request_id(sr.getStudent_Request_id());
        f.setTeacher_id(a.getTeacher_id());
        f.setCost(a.getCost());
        f.setProcess_status("Completed");
        f.setFinancialReturn(a.getCost() * 0.15);
        financialReturnRepository.save(f);

        FinancialReturn x=financialReturnRepository.getFinancialReturnByStudent_request(student_Request);
        financialReturnRepository.delete(x);
    }

    public List<Teacher_Balance> displayBalance(int teacher_id){
        List<Teacher_Balance> tb = teacher_BalanceRepository.findBalanceByTeacherId(teacher_id);
        if (tb.isEmpty()){
            throw new ApiException("Teacher Balance is empty");
        }
        return tb;
    }

    //teacher can see students comments in its offers or applications
    public List<Comments> application_Comments(int teacher_id){
        Teacher t = teacherRepository.findTeacherById(teacher_id);
        if (t == null) {
            throw new ApiException("Teacher Not Found");
        }
        Application a = applicationRespository.findByTeacherId(teacher_id);
        if (a == null) {
            throw new ApiException("Teacher didn't publish Application");
        }
        List<Comments> ct = commentRepository.findByApplicationId(a.getApplication_id());
        if (ct == null) {
            throw new ApiException("Comment Not Found");
        }
        return ct;
    }

}
