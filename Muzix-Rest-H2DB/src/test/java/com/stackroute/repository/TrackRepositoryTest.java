package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


    @RunWith(SpringRunner.class)
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class TrackRepositoryTest {

        @Autowired
        private TrackRepository trackRepository;

        private Track track;

        @Before
        public void setUp() throws Exception {
            Track track = new Track();
            track.setTrackName("believer");
            track.setTrackComment("Imagine dragons fav compo");
        }

        @Test
        public void repositoryTest() {
            Track actual = trackRepository.save(track);
            Optional<Track> optional = trackRepository.findById(actual.getTrackId());
            assertEquals(optional.get(), actual);
        }


}