package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    //to override the track to save
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Optional<Track> fetchtrack = trackRepository.findById(track.getTrackId());
        if(fetchtrack.isPresent()) {
            throw new TrackAlreadyExistsException("Track already Exists(-_-)");
        }
        else {
            return trackRepository.save(track);
        }

    }

    //to override to get all tracks
    @Override
    public List<Track> getAllTracks() {
        List<Track> trackList = (List<Track>)trackRepository.findAll();
        return trackList;
    }


    //to override the delete for the interface
    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException {
        Optional<Track> track1 = trackRepository.findById(id);
        if(trackRepository.existsById(id))
            trackRepository.deleteById(id);
        else {
            throw new TrackNotFoundException("Track not found");
        }
        return track1.get();
    }


    // To override the update for the interface
    @Override
    public Track updateByTrackId(int trackId, Track track) throws TrackNotFoundException {

        Track track1;
        if(trackRepository.existsById(trackId))
        {
            track1 = trackRepository.save(track);

        }
        else{
            throw new TrackNotFoundException("Track not found with \" "+trackId+ "\" to update the Track");
        }
        return track1;
    }

    //To find the tracks by Name
    @Override
    public List<Track> displayTrackByName(String trackName) throws TrackNotFoundException {
        List<Track> trackList=trackRepository.findByTrackName(trackName);
        if (trackList.isEmpty()){
            throw new TrackNotFoundException("Track with \""+ trackName+"\" Track-Name, does not exist");
        }
        return trackList;
    }





}
