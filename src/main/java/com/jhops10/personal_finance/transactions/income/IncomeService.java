package com.jhops10.personal_finance.transactions.income;

import com.jhops10.personal_finance.exceptions.UserNotFoundException;
import com.jhops10.personal_finance.user.User;
import com.jhops10.personal_finance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;


    public Income createIncome(IncomeDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("Usuário com o id: " + dto.userId() + " não encontrado"));

        Income income = Income.builder()
                .description(dto.description())
                .amount(dto.amount())
                .date(dto.date())
                .user(user)
                .build();

        return incomeRepository.save(income);
    }

    public List<Income> getIncomesByUser(Long userId) {
        return incomeRepository.findByUserId(userId);
    }
}
