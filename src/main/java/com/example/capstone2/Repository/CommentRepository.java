package com.example.capstone2.Repository;

import com.example.capstone2.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    @Query("select sc from Comments sc where sc.student_id=?1")
    List<Comments> findByStudentId(int student_id);

    @Query("select sc from Comments sc where sc.comment_id=?1")
    Comments findByCommentId(int comment_id);

    @Query("select ct from Comments ct where ct.application_id=?1")
    List<Comments> findByApplicationId(int application_id);
}
