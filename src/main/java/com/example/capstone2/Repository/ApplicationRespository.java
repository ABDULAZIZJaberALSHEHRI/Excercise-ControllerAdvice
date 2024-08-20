package com.example.capstone2.Repository;

import com.example.capstone2.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRespository extends JpaRepository<Application, Integer> {
    @Query("select a from Application a where a.application_id=?1")
    Application findByApplicationId(int application_id);

    @Query("select t from Application t where t.teacher_id=?1")
    Application findByTeacherId(int teacher_id);
}
