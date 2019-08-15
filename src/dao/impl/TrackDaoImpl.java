package dao.impl;

import dao.TrackDao;
import entities.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoImpl extends AbstractDao implements TrackDao {

    private static final String GET_TRACKS_BY_TYPE_QUERY = "SELECT * FROM tracks WHERE track_type=?";
    private static final String GET_ALL_TRACKS_QUERY = "SELECT * FROM tracks";
    private static final String DELETE_TRACK_QUERY = "DELETE FROM tracks WHERE track_id=?";
    private static final String UPDATE_TRACK_QUERY = "UPDATE tracks SET track_name=?, track_length=?," +
            "track_type=? WHERE track_id=?";
    private static final String ADD_TRACK_QUERY = "INSERT INTO tracks" +
            "(track_name, track_length, track_type) VALUES (?, ?, ?)";

    private PreparedStatement getTracksByType;
    private PreparedStatement getAllTracks;
    private PreparedStatement addTrack;
    private PreparedStatement updateTrack;
    private PreparedStatement deleteTrack;

    private static volatile TrackDao INSTANCE = null;

    public static TrackDao getInstance() {

        TrackDao trackDao = INSTANCE;
        if (trackDao == null) {
            synchronized (TrackDaoImpl.class) {
                trackDao = INSTANCE;
                if (trackDao == null) {
                    INSTANCE = trackDao = new TrackDaoImpl();
                }
            }
        }
        return trackDao;
    }

    private Track populateEntity(ResultSet resultSet) throws SQLException {

        Track entity = new Track();

        entity.setTrack_id(resultSet.getLong(1));
        entity.setTrack_name(resultSet.getString(2));
        entity.setTrack_length(resultSet.getInt(3));
        entity.setTrack_type(resultSet.getString(4));

        return entity;
    }

    @Override
    public List<Track> getByType(String track_type) throws SQLException {

        getTracksByType = preparedStatement(GET_TRACKS_BY_TYPE_QUERY);
        getTracksByType.setString(1, track_type);

        List<Track> trackList = new ArrayList<>();
        ResultSet resultSet = getTracksByType.executeQuery();

        while (resultSet.next()) {
            trackList.add(populateEntity(resultSet));
        }
        close(resultSet);
        return trackList;
    }

    @Override
    public Track add(Track track) throws SQLException{

            addTrack = preparedStatement(ADD_TRACK_QUERY);
            addTrack.setString(1, track.getTrack_name());
            addTrack.setInt(2, track.getTrack_length());
            addTrack.setString(3, track.getTrack_type());
            addTrack.executeUpdate();

        return track;
    }

    @Override
    public List<Track> getAll() throws SQLException {

        getAllTracks = preparedStatement(GET_ALL_TRACKS_QUERY);

        List<Track> trackList = new ArrayList<>();
        ResultSet resultSet = getAllTracks.executeQuery();

        while (resultSet.next()) {
            trackList.add(populateEntity(resultSet));
        }
        close(resultSet);

        return trackList;
    }

    @Override
    public Track update(Track track) throws SQLException {

        updateTrack = preparedStatement(UPDATE_TRACK_QUERY);
        updateTrack.setString(1, track.getTrack_name());
        updateTrack.setInt(2, track.getTrack_length());
        updateTrack.setString(3, track.getTrack_type());
        updateTrack.setLong(4, track.getTrack_id());
        updateTrack.executeUpdate();

        return track;
    }

    @Override
    public void delete(long track_id) throws SQLException {

        deleteTrack = preparedStatement(DELETE_TRACK_QUERY);
        deleteTrack.setLong(1, track_id);
        deleteTrack.executeUpdate();
    }
}
