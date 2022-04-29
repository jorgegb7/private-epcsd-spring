package edu.uoc.epcsd.showcatalog.controllers;

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
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private KafkaTemplate<String, Show> kafkaTemplate;

    //get all shows
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Show> getAllShows() {
        log.trace("Get All Shows");
        return showRepository.findAll();
    }

    //create show
    @PostMapping("/")
    public Show createShow(@RequestBody Show show) {
        log.trace("Create Show");
        Show finalShow = showRepository.save(show);
        // TODO: NOTIFY
        return finalShow;
    }

    //delete show
    @DeleteMapping("/{showId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShow(@PathVariable Long showId) {

        if (showRepository.existsById(showId)){
            log.trace("Delete Show");
            showRepository.deleteById(showId);
        } else {
            log.trace("Show does not exist");
        }
    }
}
