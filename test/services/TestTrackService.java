//package services;
//
//import entities.Track;
//import entities.User;
//import services.impl.TrackServiceImpl;
//import services.impl.UserServiceImpl;
//
//public class TestTrackService {
//
//    TrackServiceImpl trackSI = new TrackServiceImpl();
//    UserServiceImpl userSI = new UserServiceImpl();
//
//    private static User user = new User();
//    private static Track track = new Track();
//
//    public static void main(String[] args) {
//
//        TestTrackService run = new TestTrackService();
//////        run.methodGetAllTracks();
//////        run.methodGetByType();
//        run.methodAddTrack();
//////        run.methodUpdateTrack();
//////        run.methodDeleteTrack();
////        run.first();
//    }
//
//    private void first() {
//
//        for (User users : userSI.getUserByUsername("JD1")) {
//            System.out.println(users.getUsername());
//        }
//        for (User users : userSI.getUsersByRole("admin")) {
//            System.out.println(users.getUsername());
//        }
//    }
//
//    private void methodGetAllTracks() {
//
//        System.out.println("Get all tracks list");
//        for (Track tracks : trackSI.getAll()) {
//            System.out.println(tracks.getTrack_id() + " " + tracks.getTrack_name() +
//                    " " + tracks.getTrack_length() + " " + tracks.getTrack_type());
//        }
//    }
//
//    private void methodGetByType() {
//
//        System.out.println("Get tracks by type");
//        for (Track tracks : trackSI.getByType("DRIFT")) {
//            System.out.println(tracks.getTrack_id() + " " + tracks.getTrack_name() +
//                    " " + tracks.getTrack_length() + " " + tracks.getTrack_type());
//        }
//    }
//
//    private void methodAddTrack() {
//
//        System.out.println("Add track: track_name, track_length, track_type");
//        track.setTrack_name("test");
//        track.setTrack_length(999);
//        track.setTrack_type("CIRCUIT");
//        trackSI.add(track);
//    }
//    private void methodUpdateTrack() {
//
//        System.out.println("Update track: track_length WHERE track_id=?");
//        track.setTrack_length(0);
//        track.setTrack_id(6);
//        trackSI.update(track);
//    }
//    private void methodDeleteTrack() {
//
//        System.out.println("Delete track WHERE track_id=?");
//        trackSI.delete(5);
//    }
//}
