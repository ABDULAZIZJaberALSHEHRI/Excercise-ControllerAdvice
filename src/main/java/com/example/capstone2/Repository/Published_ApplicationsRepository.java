package com.example.capstone2.Repository;

import com.example.capstone2.Model.Published_Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Published_ApplicationsRepository extends JpaRepository<Published_Applications, Integer> {

    @Query("select p from Published_Applications p where p.published_Applications_id=?1")
    Published_Applications findByPublished_Applications_id(int id);

    @Query("select t from Published_Applications t where t.teacher_id=?1")
    List<Published_Applications> findByTeacher_id(int id);
}
