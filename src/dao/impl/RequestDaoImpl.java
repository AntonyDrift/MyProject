package dao.impl;

import dao.RequestDao;
import entities.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl extends AbstractDao implements RequestDao {

    private static final String GET_ALL_REQUESTS_QUERY = "SELECT * FROM requests";
    private static final String DELETE_REQUEST_QUERY = "DELETE FROM requests WHERE request_id=?";
    private static final String UPDATE_REQUEST_QUERY = "UPDATE requests SET request_date=?," +
            "client_id=?, car_id=?, track_id=?, request_status=?, cost=? WHERE request_id=?";
    private static final String GET_REQUESTS_BY_CLIENT_EMAIL_QUERY = "SELECT * FROM requests" +
            "       JOIN clients ON requests.client_id = clients.client_id" +
            "       WHERE clients.email = ?";
    private static final String ADD_REQUEST_QUERY = "INSERT INTO requests" +
            "       (request_date, client_id, car_id, track_id, request_status, cost)" +
            "       VALUES (?, ?, ?, ?, ?, ?)";

    private PreparedStatement updateRequest;
    private PreparedStatement deleteRequest;
    private PreparedStatement addRequest;
    private PreparedStatement getRequestsByClientEmail;
    private PreparedStatement getAllRequests;

    private static volatile RequestDao INSTANCE = null;

    public static RequestDao getInstance() {

        RequestDao requestDao = INSTANCE;
        if (requestDao == null) {
            synchronized (RequestDaoImpl.class) {
                requestDao = INSTANCE;
                if (requestDao == null) {
                    INSTANCE = requestDao = new RequestDaoImpl();
                }
            }
        }
        return requestDao;
    }

    private Request populateEntities(ResultSet resultSet) throws SQLException {

        Request entity = new Request();

        entity.setRequest_id(resultSet.getLong(1));
        entity.setRequest_date(resultSet.getString(2));
        entity.setClient_id(resultSet.getLong(3));
        entity.setCar_id(resultSet.getLong(4));
        entity.setTrack_id(resultSet.getLong(5));
        entity.setRequest_status(resultSet.getInt(6));
        entity.setCost(resultSet.getInt(7));

        return entity;
    }

//    @Override
//    public List<Request> getByUserEmail(String user_email) throws SQLException {
//
//        getRequestsByClientEmail = preparedStatement(GET_REQUESTS_BY_CLIENT_EMAIL_QUERY);
//        getRequestsByClientEmail.setString(1, user_email);
//
//        List<Request> requestList = new ArrayList<>();
//        ResultSet resultSet = getRequestsByClientEmail.executeQuery();
//
//        while (resultSet.next()) {
//            requestList.add(populateEntities(resultSet));
//        }
//
//        close(resultSet);
//        return requestList;
//    }

    @Override
    public Request add(Request request) throws SQLException {

        addRequest = preparedStatement(ADD_REQUEST_QUERY);
        addRequest.setString(1, request.getRequest_date());
        addRequest.setLong(2, request.getClient_id());
        addRequest.setLong(3, request.getCar_id());
        addRequest.setLong(4, request.getTrack_id());
        addRequest.setInt(5, request.getRequest_status());
        addRequest.setInt(6, request.getCost());
        addRequest.executeUpdate();

        return request;
    }

    @Override
    public List<Request> getAll() throws SQLException {

        getAllRequests = preparedStatement(GET_ALL_REQUESTS_QUERY);

        List<Request> requestsList = new ArrayList<>();
        ResultSet resultSet = getAllRequests.executeQuery();

        while (resultSet.next()) {
            requestsList.add(populateEntities(resultSet));
        }
        close(resultSet);
        return requestsList;
    }

    @Override
    public Request update(Request request) throws SQLException {

        updateRequest = preparedStatement(UPDATE_REQUEST_QUERY);
        updateRequest.setString(1, request.getRequest_date());
        updateRequest.setLong(2, request.getClient_id());
        updateRequest.setLong(3, request.getCar_id());
        updateRequest.setLong(4, request.getTrack_id());
        updateRequest.setInt(5, request.getRequest_status());
        updateRequest.setInt(6, request.getCost());
        updateRequest.setLong(7, request.getRequest_id());
        updateRequest.executeUpdate();

        return request;
    }

    @Override
    public void delete(long request_id) throws SQLException {

        deleteRequest = preparedStatement(DELETE_REQUEST_QUERY);
        deleteRequest.setLong(1, request_id);
        deleteRequest.executeUpdate();
    }
}
