package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class TrackController {

    private ResponseEntity<?> responseEntity;

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }


    //Request Mapping for POST
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){

        try {
            Track track1 = trackService.saveTrack(track);
            responseEntity=  new ResponseEntity<Track>(track1, HttpStatus.OK);
        }
        catch (TrackAlreadyExistsException trackAlreadyExistsException){
            responseEntity = new ResponseEntity<>(trackAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    //Request Mapping for GET
    @GetMapping("tracks")
    public ResponseEntity<List<Track>> listOfTracks(){
        List<Track> allTracks = trackService.getAllTracks();
        return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
    }

    //Request Mapping for DELETE
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id){
        try {
            Track track2 = trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<Track>(track2, HttpStatus.OK);
        }
        catch (TrackNotFoundException trackNotFoundException){
            responseEntity = new ResponseEntity<>(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }

    //Request Mapping for Update(PUT)
    @PutMapping
("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable(value = "id") int trackId, @RequestBody Track track){
        try {
            Track track1 = (Track) trackService.updateByTrackId(trackId, track);
            return new ResponseEntity<Track>(track1, HttpStatus.OK);
        } catch (TrackNotFoundException trackNotFoundException) {
            return new ResponseEntity<>(trackNotFoundException.getMessage(), HttpStatus.CONFLICT);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //Request Mapping for GET (GetByTrackName)
    @GetMapping(value = "track/{trackName}")
    public ResponseEntity<?> findTrackByTrackName(@PathVariable(value = "trackName") String trackName){
        ResponseEntity responseEntity;

        try{
            List<Track> trackList=trackService.displayTrackByName(trackName);
            responseEntity=new ResponseEntity<List<Track>>(trackList,HttpStatus.OK);
        }catch (TrackNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.GONE);
        }
        return responseEntity;
    }





}
