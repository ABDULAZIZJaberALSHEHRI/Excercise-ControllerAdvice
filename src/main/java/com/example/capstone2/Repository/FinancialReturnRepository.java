package com.example.capstone2.Repository;

import com.example.capstone2.Model.FinancialReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialReturnRepository extends JpaRepository<FinancialReturn, Integer> {


    @Query("select f from FinancialReturn f where f.financialReturn_Id=?1")
    FinancialReturn getFinancialReturnByFinancialReturn_Id(int id);

    @Query("select s from FinancialReturn s where s.student_request_id=?1 and s.process_status='bending'")
    FinancialReturn getFinancialReturnByStudent_request(int id);
}
