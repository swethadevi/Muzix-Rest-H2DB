package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource(value="startup.properties")
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

    private TrackRepository trackRepository;

    private static final Logger log = Logger.getLogger(StartupApplicationListener.class);

    @Autowired
    public StartupApplicationListener(TrackRepository trackRepository) {
        this.trackRepository=trackRepository;
    }

    //By using the @value for the application startup to remove hard coded data to application properties
    @Value("${trackName1}")
    private String tname;

    @Value("${trackComment1}")
    private String tcomment;

    //by using ​​ Environment to remove hard coded data from  application code to application properties
    @Autowired
    private Environment environment;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("Entering Data On Start up using ApplicationListener Approach");

        Track musicTrack1= new Track();
        musicTrack1.setTrackId(1);
        musicTrack1.setTrackName(tname); //fetching the variables data from application properties by using @value
        musicTrack1.setTrackComment(tcomment); //fetching data from application properties
        trackRepository.save(musicTrack1);


        Track musicTrack2=new Track();
        musicTrack2.setTrackId(2);
        musicTrack2.setTrackName(environment.getProperty("trackName2")); //using environment to fetch data from application properties
        musicTrack2.setTrackComment(environment.getProperty("trackComment2")); // fetching the data from the application properties using environment
        trackRepository.save(musicTrack2);

        log.info("Initial data entered using ApplicationListener");
    }
}