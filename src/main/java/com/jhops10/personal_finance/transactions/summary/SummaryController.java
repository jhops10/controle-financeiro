package com.jhops10.personal_finance.transactions.summary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<SummaryDTO> getMonthlySummary(@PathVariable("userId") Long userId, @RequestParam int month, @RequestParam int year) {
        SummaryDTO summary = summaryService.getSummary(userId, month, year);
        return ResponseEntity.ok(summary);
    }


}
