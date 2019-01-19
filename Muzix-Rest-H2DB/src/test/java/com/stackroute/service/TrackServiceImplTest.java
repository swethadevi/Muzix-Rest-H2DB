package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TrackServiceImplTest {

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;

    Track track;
    List<Track> track1;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track1 = new ArrayList<>();
        track = new Track("Sun raha hai na tu","Ankit tiwari singer");
        track1.add(track);
        track1.add(new Track("Faded","Alan walker"));
    }

    //Test case for the the save track post operation
    @Test
    public void saveTrack() throws  TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(track);
        Track actual = trackService.saveTrack(track);
        assertEquals(track, actual);
        verify(trackRepository, times(1)).save(track);
    }


    //Test case for get operation
    @Test
    public void getAllTracks() throws Exception{
        when(trackRepository.findAll()).thenReturn(track1);
        List<Track> actual = trackService.getAllTracks();
        assertEquals(track1,actual);
        verify(trackRepository, Mockito.times(1)).findAll();
    }

    //Test case to check for delete operation
    @Test
    public void deleteTrack() throws TrackNotFoundException, TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(track);
        Track actual = trackService.saveTrack(track);
        doNothing().when(trackRepository).deleteById(5);
//       trackRepository.deleteById(track.getTrackId());
//        assertTrue(trackService.deleteTrack(track.getTrackId()));
    }


    //Test case to check for search track by name
    @Test
    public void displayTrackByName() throws TrackNotFoundException, TrackAlreadyExistsException {
        when(trackRepository.save(track)).thenReturn(track);
        trackService.saveTrack(track);
        List<Track> expected = new ArrayList<>();
        expected.add(track);
        when(trackRepository.findByTrackName("Kaun tujhe")).thenReturn(expected);
        List<Track> actual = trackService.displayTrackByName("Kaun tujhe");
        assertArrayEquals(expected.toArray(), actual.toArray());
        verify(trackRepository, Mockito.times(1)).findByTrackName("Kaun tujhe");
    }


    //Test case for the update operation
    @Test
    public void updateByTrackId() throws TrackAlreadyExistsException, TrackNotFoundException {
        track = new Track("track1","comment");
        when(trackRepository.existsById(1)).thenReturn(true);
        when(trackRepository.save(track)).thenReturn(track);
        Track actual = trackService.updateByTrackId(1,track);
        assertEquals(track,actual);


    }


}

