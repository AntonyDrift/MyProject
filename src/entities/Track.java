package entities;

public class Track {

    private long track_id;
    private String track_name;
    private int track_length;
    private String track_type;

    public Track() {
    }

    public long getTrack_id() {
        return track_id;
    }

    public void setTrack_id(long track_id) {
        this.track_id = track_id;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public int getTrack_length() {
        return track_length;
    }

    public void setTrack_length(int track_length) {
        this.track_length = track_length;
    }

    public String getTrack_type() {
        return track_type;
    }

    public void setTrack_type(String track_type) {
        this.track_type = track_type;
    }
}
