package maintesting;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/merchants")
public class MerchantsResource {
    SimpleDTUPay simpleDTUPay = SimpleDTUPay.instance;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMerchant(Merchant merchant){
        simpleDTUPay.addMerchant(merchant);
    }
}
