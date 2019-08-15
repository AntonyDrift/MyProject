package services.impl;

import dao.RequestDao;
import dao.impl.RequestDaoImpl;
import entities.Request;
import org.apache.log4j.Logger;
import services.RequestService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl extends AbstractService implements RequestService {

    private static volatile RequestService INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);
    private RequestDao requestDao = RequestDaoImpl.getInstance();

    public static boolean requestErrorStatusLog;

    public static RequestService getInstance() {
        RequestService requestService = INSTANCE;
        if (requestService == null) {
            synchronized (RequestServiceImpl.class) {
                requestService = INSTANCE;
                if (requestService == null) {
                    INSTANCE = requestService = new RequestServiceImpl();
                }
            }
        }
        return requestService;
    }

//    @Override
//    public List<Request> getByClientEmail(String client_email) {
//
//        List<Request> requestsByClientEmail = new ArrayList<>();
//        try {
//            requestsByClientEmail = requestDao.getByUserEmail(client_email);
//            requestErrorStatusLog=false;
//        } catch (SQLException e) {
//            LOGGER.error("Error getting requests by client email: " + client_email);
//            requestErrorStatusLog=true;
//        }
//        return requestsByClientEmail;
//    }

    @Override
    public Request add(Request request) {

        try {
            request = requestDao.add(request);
            requestErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error adding request" + request);
            requestErrorStatusLog=true;
        }
        return request;
    }

    @Override
    public Request update(Request request) {

        try {
            request = requestDao.update(request);
            requestErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error update request" + request);
            requestErrorStatusLog=true;
        }
        return request;
    }

    @Override
    public void delete(long request_id) {

        try {
            requestDao.delete(request_id);
            requestErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error delete request ID# " + request_id);
            requestErrorStatusLog=true;
        }

    }

    @Override
    public List<Request> getAll() {

        List<Request> allRequests = new ArrayList<>();
        try {
            allRequests = requestDao.getAll();
            requestErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error getting all requests");
            requestErrorStatusLog=true;
        }
        return allRequests;
    }
}
