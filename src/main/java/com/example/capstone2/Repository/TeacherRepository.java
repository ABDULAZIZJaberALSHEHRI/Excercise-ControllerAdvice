package com.example.capstone2.Repository;

import com.example.capstone2.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select t from Teacher t where t.teacher_id=?1")
    Teacher findTeacherById(int id);
}
