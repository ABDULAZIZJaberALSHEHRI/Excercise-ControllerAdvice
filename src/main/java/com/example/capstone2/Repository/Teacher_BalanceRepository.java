package com.example.capstone2.Repository;

import com.example.capstone2.Model.Teacher_Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Teacher_BalanceRepository extends JpaRepository<Teacher_Balance, Integer> {

    @Query("select tb from Teacher_Balance tb where tb.teacher_id=?1")
    Teacher_Balance findByTeacherId(int teacher_id);

    @Query("select t from Teacher_Balance t where t.teacher_id=?1")
    List<Teacher_Balance> findBalanceByTeacherId(int teacher_id);
}
