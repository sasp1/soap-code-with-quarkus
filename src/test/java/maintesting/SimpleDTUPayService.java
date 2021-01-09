package maintesting;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class SimpleDTUPayService {
    private Client client;
    private WebTarget baseUrl;
    private Payment payment;

    public SimpleDTUPayService() {
        client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8080/");
    }

    public boolean pay(int amount, String cid, String mid, String cprNumber, String mCprNumber) {
        payment = new Payment(amount, new Customer(cid, cprNumber), new Merchant(mid, cprNumber));

        try{
            String hej = baseUrl.path("payments").request().post(Entity.json(payment), String.class);
        } catch (BadRequestException | NotFoundException e) {
            return false;
        }
        return true;
    }

    public void addMerchant(Merchant merchant) {
        baseUrl.path("merchants").request().post(Entity.json(merchant), String.class);
    }

    public void addCustomer(Customer customer) {
        baseUrl.path("customers").request().post(Entity.json(customer), String.class);
    }
}
