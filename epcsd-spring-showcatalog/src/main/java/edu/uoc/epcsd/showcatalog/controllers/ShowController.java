package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    //get all shows
    @GetMapping("/allShows")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getAllShows() {
        log.trace("Get All Shows");
        return showRepository.findAll();
    }

    //create show
    @PostMapping("/createShow")
    public Show createShow(@RequestBody Show show) {
        log.trace("Create Show");
        // TODO: NOTIFY
        return showRepository.save(show);
    }

    //delete show
    @DeleteMapping("/delete/{showId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShow(@PathVariable Long showId) {

        if (showRepository.existsById(showId)){
            log.trace("Delete Show");
            showRepository.deleteById(showId);
        } else {
            log.trace("Show does not exist");
        }
    }

    // get shows by name
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByName(@PathVariable("name") String name) {
        log.trace("Search by name");
        return showRepository.findShowsByName(name);
    }

    // get shows by categories
    @GetMapping("/category/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getShowsByCategories(@PathVariable("name") String name) {
        log.trace("Search by name");
        return showRepository.findShowsByCategoryName(name);
    }



//   //create performance
//    @PostMapping("/")
//    public Performance createPerformance(@RequestBody Performance performance) {
//        log.trace("Create Performance");
//        Performance finalPerformance = performanceRepository.save(performance);
//        return finalPerformance;
//    }
//
//    //delete show
//    @DeleteMapping("/{performanceId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deletePerformance(@PathVariable Long performanceId) {
//
//        if (performanceRepository.existsById(performanceId)){
//            log.trace("Delete Performance");
//            performanceRepository.deleteById(performanceId);
//        } else {
//            log.trace("Performance does not exist");
//        }
//
//    }
//
//    // get performance by show
//    @GetMapping("/{showName}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Performance> getPerformanceByShowName (@PathVariable String showName){
//        log.trace("getting performance by show");
//        return performanceRepository.findPerformanceByShowName(showName);
//    }


}
