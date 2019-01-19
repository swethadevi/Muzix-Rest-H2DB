package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException; //for Posting data to DB

    public Track deleteTrack(int id) throws TrackNotFoundException; //to perform delete operation on the track

    public Track updateByTrackId(int trackId, Track track) throws TrackNotFoundException; // to perform the updation operation

    public List<Track> getAllTracks(); //for getting all data from DB

    public List<Track> displayTrackByName(String trackName) throws TrackNotFoundException; //to find the track by name


}
