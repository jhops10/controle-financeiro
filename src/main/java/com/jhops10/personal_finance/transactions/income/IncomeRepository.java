package com.jhops10.personal_finance.transactions.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user.id = :userId AND MONTH(i.date) = :month AND YEAR(i.date) = :year")
    BigDecimal getTotalIncomeByMonthAndYear(Long userId, int month, int year);
}
