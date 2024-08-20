package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.*;
import com.example.capstone2.Repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRequestRepository studentRequestRepository;
    private final StudentRepository studentRepository;
    private final ApplicationRespository applicationRespository;
    private final FinancialReturnRepository financialReturnRepository;
    private final TeacherRepository teacherRepository;
    private final CommentRepository commentRepository;

    ArrayList<Double> ratings = new ArrayList<Double>();

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int student_id) {
        Student s = studentRepository.findStudentById(student_id);
        if (s == null) {
            throw new ApiException("student not found");
        }
        studentRepository.delete(s);
        StudentRequest st = studentRequestRepository.findByStudentId(student_id);
        if (st != null) {
            studentRequestRepository.delete(st);
        }

    }

    public void updateStudent(int student_id,Student student) {
        Student s = studentRepository.findStudentById(student_id);
        if (s == null) {
            throw new ApiException("student not found");
        }
        s.setF_Name(student.getF_Name());
        s.setL_Name(student.getL_Name());
        s.setEmail(student.getEmail());
        s.setAge(student.getAge());
        s.setPhoneNumber(student.getPhoneNumber());
        s.setCity(student.getCity());
        s.setEducationLevel(student.getEducationLevel());
        studentRepository.save(s);
    }


    public List<Application> displayAllApplications() {

        return applicationRespository.findAll();
    }

    //student request teacher application or offer
    public void addRequestApplication(int application_id,int student_id) {
        Application a = applicationRespository.findByApplicationId(application_id);
        Student student = studentRepository.findStudentById(student_id);


        if (a == null) {
            throw new ApiException("application not found");
        }
        if (student == null) {
            throw new ApiException("student not found");
        }
        StudentRequest s = new StudentRequest();
        s.setApplication_id(a.getApplication_id());
        s.setStudent_id(student.getStudent_id());
        s.setStudent_name(student.getF_Name());
        studentRequestRepository.save(s);

        FinancialReturn f = new FinancialReturn();
        f.setStudent_request_id(s.getStudent_Request_id());
        f.setTeacher_id(a.getTeacher_id());
        f.setCost(a.getCost());
        financialReturnRepository.save(f);
    }

    public void deleteRequestApplication(int request_id) {
        StudentRequest s = studentRequestRepository.findByStudentRequestId(request_id);
        if (s == null) {
            throw new ApiException("request not found");
        }
        studentRequestRepository.delete(s);
    }

    //student can make teacher rate after take the teacher course
    public void rate_teacher(int teacher_id,int student_id, double rate) {
        Teacher t = teacherRepository.findTeacherById(teacher_id);
        if (t == null) {
            throw new ApiException("teacher not found");
        }
        Student s = studentRepository.findStudentById(student_id);
        if (s == null) {
            throw new ApiException("student not found");
        }
        StudentRequest sr = studentRequestRepository.findByStudentId(student_id);
        if (sr == null) {
            throw new ApiException("student request not found found");
        }
        StudentRequest srt = studentRequestRepository.findByApplication_id(sr.getApplication_id());
        Application a = applicationRespository.findByApplicationId(srt.getApplication_id());
        if (a.getTeacher_id().equals(t.getTeacher_id())) {
            double sum = 0;

            ratings.add(rate);
            for (int i = 0; i < ratings.size(); i++) {
                sum += ratings.get(i);
            }
            double avg = sum / ratings.size();
            t.setRating(avg);
            teacherRepository.save(t);
        }else throw new ApiException("you must request application from selected teacher to rate him!");

    }

    //student can add comment to application or offer
    public void addComment(int application_id,int student_id,String comment) {
        Application a = applicationRespository.findByApplicationId(application_id);
        if (a == null) {
            throw new ApiException("application not found");
        }
        Student s = studentRepository.findStudentById(student_id);
        if (s == null) {
            throw new ApiException("student not found");
        }

            Comments c = new Comments();
            c.setApplication_id(a.getApplication_id());
            c.setStudent_id(student_id);
            c.setStudent_name(s.getF_Name());
            c.setComment(comment);
            commentRepository.save(c);
    }

    //display comments by student id
    public List<Comments> displayComments(int student_Id) {
        List<Comments> sc = commentRepository.findByStudentId(student_Id);
        if (sc == null) {
            throw new ApiException("student dont make any comment");
        }
        return sc;
    }

    public void deleteComment (int comment_id){
        Comments c = commentRepository.findByCommentId(comment_id);
        if (c == null) {
            throw new ApiException("comment not found");
        }
        commentRepository.delete(c);
    }

    //student can search for application or offer by course name
    public List<Application> displayApplicationsByCourse(String course) {
        List<Application> matchedApplications = new ArrayList<>();

        List<Application> apps = applicationRespository.findAll();
        for (Application a : apps) {
            if (a.getCourse_Name().equalsIgnoreCase(course)) {
                matchedApplications.add(a);
            }
        }
        if (matchedApplications.isEmpty()) {
            throw new ApiException("not match application found");
        }
        return matchedApplications;
    }


}
