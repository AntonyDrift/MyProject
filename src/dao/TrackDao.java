package dao;

import entities.Track;

import java.sql.SQLException;
import java.util.List;

public interface TrackDao extends GenericDao<Track> {

    List<Track> getByType(String track_type) throws SQLException;
}
