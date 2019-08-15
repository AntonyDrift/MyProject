package web.servlets.admin;

import entities.Track;
import services.TrackService;
import services.impl.TrackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static services.impl.TrackServiceImpl.trackErrorStatusLog;

@WebServlet(urlPatterns = {"/accounts/admin/edittracks"}, name = "adminTracks")
public class AdminTrackServlet extends HttpServlet {

    private TrackService trackService = TrackServiceImpl.getInstance();
    private static Track track = new Track();
    private static AdminTrackServlet run = new AdminTrackServlet();

    private void attributeLists(HttpServletRequest req) {

        List<Track> trackList = trackService.getAll();
        req.setAttribute("tracks", trackList);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        run.attributeLists(req);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editTracks.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String message = null;

        String delete_track = req.getParameter("delete");
        String update_track = req.getParameter("update");
        String add_track = req.getParameter("add");

        String track_name = req.getParameter("track_name");
        String track_length = req.getParameter("track_length");
        String track_type = req.getParameter("track_type");

        if (delete_track != null) {
            if (req.getParameter("track_id") == null) {
                message = "Choose track on table";

            } else {

                trackService.delete(Long.parseLong(req.getParameter("track_id")));
                if (!trackErrorStatusLog) {
                    message = "Track successfully deleted";
                }
            }
        } else if (track_name == "" | track_length == "" | track_type == "") {
            message = "Enter fields, please";

        } else {
            track.setTrack_name(track_name);
            track.setTrack_length(Integer.parseInt(track_length));
            track.setTrack_type(track_type);

            if (update_track != null) {

                if (req.getParameter("track_id") == null) {
                    message = "Choose track on table";

                } else {
                    track.setTrack_id(Long.parseLong(req.getParameter("track_id")));
                    trackService.update(track);
                    if (!trackErrorStatusLog) {
                        message = "Track successfully updated";
                    }
                }
            } else if (add_track != null) {
                trackService.add(track);
                if (!trackErrorStatusLog) {
                    message = "Track successfully added";
                }
            }
        }
        run.attributeLists(req);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editTracks.jsp").forward(req, resp);
    }
}