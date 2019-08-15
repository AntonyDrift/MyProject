//package services;
//
//import dao.impl.RequestDaoImpl;
//import entities.Request;
//import services.impl.RequestServiceImpl;
//
//import java.sql.SQLException;
//
//class TestRequestService {
//
//    RequestServiceImpl requestSI = new RequestServiceImpl();
//    private static Request request = new Request();
//
//    public static void main(String[] args) throws SQLException {
//
//        TestRequestService run = new TestRequestService();
//        run.methodGetAllRequests();
//        run.methodGetRequestsByClientEmail();
////        run.methodAddRequest();
//        run.methodUpdateRequest();
////        run.methodDeleteRequest();
//    }
//    private void methodGetAllRequests() {
//
//        System.out.println("All requests list");
//        for (Request requests : requestSI.getAll()) {
//            System.out.println(requests.getRequest_id() + " " +
//                    requests.getRequest_date() + " " + requests.getClient_id() + " " +
//                    requests.getCar_id() + " " + requests.getTrack_id() + " " +
//                    requests.getRequest_status() + " " + requests.getCost());
//        }
//    }
//    private void methodGetRequestsByClientEmail() {
//
//        System.out.println("Request list by client email");
//        for (Request requests : requestSI.getByClientEmail("info@test.com")) {
//            System.out.println(requests.getRequest_id() + " " +
//                    requests.getRequest_date() + " " + requests.getClient_id() + " " +
//                    requests.getCar_id() + " " + requests.getTrack_id() + " " +
//                    requests.getRequest_status() + " " + requests.getCost());
//        }
//    }
//    private void methodAddRequest() {
//
//        System.out.println("Add request method: request_date, client_id, car_id, " +
//                "track_id, request_status, cost");
//        request.setRequest_date("10.10.2018 23:00");
//        request.setClient_id(1);
//        request.setCar_id(2);
//        request.setTrack_id(3);
//        request.setRequest_status(0);
//        request.setCost(1000);
//        requestSI.add(request);
//
//    }
//    private void methodUpdateRequest() {
//
//        System.out.println("Update request method: request_status WHERE request_id=?");
//        request.setRequest_status(1);
//        request.setRequest_id(3);
//        request.setCost(99);
//        requestSI.update(request);
//    }
//    private void methodDeleteRequest() {
//
//        System.out.println("Delete request WHERE request_id=?");
//        requestSI.delete(5);
//    }
//}