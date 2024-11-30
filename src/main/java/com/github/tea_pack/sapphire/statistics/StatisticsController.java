package com.github.tea_pack.sapphire.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @GetMapping("/stat")
    @ResponseBody
    public ResponseEntity<List<ExtendedStatistics.Entry>> readAll(
            @RequestBody ExtendedStatistics.StatisticsPreferences prefs) {
        ExtendedStatistics extendedStaistics = new ExtendedStatistics();
        List<ExtendedStatistics.Entry> response = new ArrayList<>();

        return ResponseEntity.ok(response);
    }
}
