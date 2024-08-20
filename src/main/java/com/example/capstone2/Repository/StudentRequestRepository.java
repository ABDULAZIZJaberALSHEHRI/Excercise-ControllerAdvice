package com.example.capstone2.Repository;

import com.example.capstone2.Model.StudentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRequestRepository extends JpaRepository<StudentRequest, Integer> {
    @Query("select r from StudentRequest r where r.student_Request_id=?1")
    StudentRequest findByStudentRequestId(int id);

    @Query("select a from StudentRequest a where a.application_id=?1")
    List<StudentRequest> findByApplicationId(int application_id);

    @Query("select s from StudentRequest s where s.application_id=?1")
    StudentRequest findByApplication_id(int application_id);

    @Query("select st from StudentRequest st where st.student_id=?1")
    StudentRequest findByStudentId(int student_id);
}
