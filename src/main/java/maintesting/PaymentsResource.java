package maintesting;

import dtu.ws.fastmoney.BankServiceService;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/payments")
public class PaymentsResource {
    SimpleDTUPay simpleDTUPay = SimpleDTUPay.instance;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(Payment payment){
        var bank = new BankServiceService().getBankServicePort();



        try {
            Payment addedPayment = simpleDTUPay.createPayment(payment);
            return Response.created(new URI("/payments/" + addedPayment.hashCode())).build();
        } catch (CustomerNotFound customerNotFound) {
//            return Response.status(404).entity("whaat").build();
            throw new BadRequestException("Customer with id " + payment.getCustomer().getId() + " was not found");
        } catch (MerchantNotFound merchantNotFound) {
            throw new BadRequestException("Merchant with id " + payment.getMerchant().getId()+ " was not found");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new BadRequestException("");
        }
    }


}
