package entities;

public class Client {

    private long client_id;
    private String surname;
    private String email;
    private int phone_number;

    public Client(){
    }

    public Client(String surname, String email, int phone_number) {

        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
