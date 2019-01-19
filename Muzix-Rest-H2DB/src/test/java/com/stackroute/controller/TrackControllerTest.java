package com.stackroute.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
    public class TrackControllerTest {


    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private TrackServiceImpl trackService;

    @InjectMocks
    TrackController trackController;

    private Track track;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        track = new Track(1, "Kaun tujhe", "MS Dhoni movie fav song");
        track = new Track(4,"Alone","Alan walker fav compo");

    }

    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;

    }

    //Test case to perform post operation
    @Test
    public void saveTrack() throws Exception {
        when(trackService.saveTrack(track)).thenReturn(track);
        mockMVC.perform(post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk());

        verify(trackService, Mockito.times(1)).saveTrack(Mockito.any(Track.class));
        verifyNoMoreInteractions(trackService);
    }

    //Test case to Perform Get operation
    @Test
    public void listOfTracks() throws Exception {
        when(trackService.saveTrack(track)).thenReturn(track);
        mockMVC.perform(get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk());
//        verify(trackService, Mockito.times(1)).saveTrack(Mockito.any(Track.class));
//        verifyNoMoreInteractions(trackService);
    }

    //Test case to perform Delete Operation
    @Test
    public void deleteTrack() throws Exception {
        when(trackService.deleteTrack(5)).thenReturn(track);
        mockMVC.perform(delete("/api/v1/track/5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(trackService, times(1)).deleteTrack(5);
        verifyNoMoreInteractions(trackService);
    }

    //Test case to perform get Operation to find track by name
    @Test
    public void findByTrackName() throws Exception {
        List<Track> expected = new ArrayList<>();
        expected.add(track);
        when(trackService.displayTrackByName("Faded")).thenReturn(expected);
        mockMVC.perform(get("/api/v1/track/Faded")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(trackService, times(1)).displayTrackByName("Faded");
        verifyNoMoreInteractions(trackService);
    }

    //    @Test
//    public void updateTrack() {
//    }
//




}


