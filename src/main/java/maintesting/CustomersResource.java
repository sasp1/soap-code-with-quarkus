package maintesting;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public class CustomersResource {
    SimpleDTUPay simpleDTUPay = SimpleDTUPay.instance;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(Customer customer){
        simpleDTUPay.addCustomer(customer);
    }

}
