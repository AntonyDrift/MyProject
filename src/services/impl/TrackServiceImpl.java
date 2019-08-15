package services.impl;

import dao.TrackDao;
import dao.impl.TrackDaoImpl;
import entities.Track;
import org.apache.log4j.Logger;
import services.TrackService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackServiceImpl extends AbstractService implements TrackService {

    private static final Logger LOGGER = Logger.getLogger(TrackServiceImpl.class);
    private static volatile TrackService INSTANCE = null;
    private TrackDao trackDao = TrackDaoImpl.getInstance();
    public static boolean trackErrorStatusLog=false;

    public static TrackService getInstance() {
        TrackService trackService = INSTANCE;
        if (trackService == null) {
            synchronized (TrackServiceImpl.class) {
                trackService = INSTANCE;
                if (trackService == null) {
                    INSTANCE = trackService = new TrackServiceImpl();
                }
            }
        }
        return trackService;
    }

    @Override
    public List<Track> getByType(String track_type) {

        List<Track> tracksByType = new ArrayList<>();
        try {
            tracksByType = trackDao.getByType(track_type);
            trackErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error get track list by type " + track_type);
            trackErrorStatusLog = true;
        }
        return tracksByType;
    }

    @Override
    public Track add(Track track) {
        try {
            track = trackDao.add(track);
            trackErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error add track " + track);
            trackErrorStatusLog = true;
        }
        return track;
    }

    @Override
    public Track update(Track track) {
        try {
            track = trackDao.update(track);
            trackErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error update track " + track);
            trackErrorStatusLog = true;
        }
        return track;
    }

    @Override
    public void delete(long track_id) {
        try {
            trackDao.delete(track_id);
            trackErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error delete track by ID#" + track_id);
            trackErrorStatusLog = true;
        }
    }

    @Override
    public List<Track> getAll() {

        List<Track> allTracks = new ArrayList<>();

        try {
            allTracks = trackDao.getAll();
            trackErrorStatusLog = false;
        } catch (SQLException e) {
            LOGGER.error("Error get all track list");
            trackErrorStatusLog = true;
        }
        return allTracks;
    }
}
