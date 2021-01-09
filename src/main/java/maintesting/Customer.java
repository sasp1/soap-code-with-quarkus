package maintesting;

public class Customer {

    private String id;
    private String cprNumber;

    public Customer() {
    }

    public Customer(String id, String cprNumber) {
        this.id = id;
        this.cprNumber = cprNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }
}
