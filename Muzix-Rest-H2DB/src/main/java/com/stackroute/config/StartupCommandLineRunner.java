package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupCommandLineRunner implements CommandLineRunner {

    TrackRepository trackRepository;

    public static final Logger log = LoggerFactory.getLogger(StartupCommandLineRunner.class);

    @Autowired
    public StartupCommandLineRunner(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Entering Data On Start using CommandLineRunner Approach");

        trackRepository.save(new Track(3,"Faded","Alan walker"));
        trackRepository.save(new Track(4,"Alone", "Alan walker fav compo"));

        log.info("Initial data entered using CommandLineRunner");
    }
}