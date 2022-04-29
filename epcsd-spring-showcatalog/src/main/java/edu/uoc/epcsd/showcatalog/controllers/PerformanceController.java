package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Performance;
import edu.uoc.epcsd.showcatalog.repositories.PerformanceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/performance")
public class PerformanceController {
    @Autowired
    private PerformanceRepository performanceRepository;

    //create performance
    @PostMapping("/")
    public Performance createPerformance(@RequestBody Performance performance) {
        log.trace("Create Performance");
        Performance finalPerformance = performanceRepository.save(performance);
        return finalPerformance;
    }

    //delete show
    @DeleteMapping("/{performanceId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerformance(@PathVariable Long performanceId) {
        log.trace("Delete Performance");
        performanceRepository.deleteById(performanceId);
    }
}
