package services;

import entities.Track;

import java.util.List;

public interface TrackService extends GenericService<Track> {

    List<Track> getByType(String track_type);
}
