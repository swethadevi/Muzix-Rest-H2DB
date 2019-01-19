package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TrackRepository extends CrudRepository<Track, Integer> {

    //Query to find by track name using @Query
    //@Query(value="select * from track tr where tr.track_name=?1 and tr.track_id>1",nativeQuery = true) //this query over writes the built in Crud repository query
    List<Track> findByTrackName(String trackName);

}
