package maintesting;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleDTUPay {
    public static SimpleDTUPay instance = new SimpleDTUPay();
    private final String customer = "cid1";
    private final String merchant = "mid1";
    List<Payment> payments = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Merchant> merchants = new ArrayList<>();

    public Payment createPayment(Payment payment) throws CustomerNotFound, MerchantNotFound {

        if (!payment.getCustomer().getId().equals(customer))
            throw new CustomerNotFound();
        if (!payment.getMerchant().getId().equals(merchant))
            throw new MerchantNotFound();

        payments.add(payment);
        var bankService = new BankServiceService().getBankServicePort();

        try {
            var cAcc = bankService.getAccountByCprNumber(payment.getCustomer().getCprNumber());
            var mAcc = bankService.getAccountByCprNumber(payment.getMerchant().getCprNumber());
            System.out.println(payment.getAmount());
            System.out.println(payment.getAmount());

            System.out.println(BigDecimal.valueOf(payment.getAmount()));
            bankService.transferMoneyFromTo(cAcc.getId(), mAcc.getId(), BigDecimal.valueOf(payment.getAmount()  ), "hmm");
            
            Account acc = bankService.getAccountByCprNumber(payment.getCustomer().getCprNumber());
            mAcc = bankService.getAccountByCprNumber(payment.getMerchant().getCprNumber());

            System.out.println(mAcc.getBalance());


            acc = bankService.getAccount(mAcc.getId());
            System.out.println(acc.getBalance());
            for (Transaction transaction : acc.getTransactions()) {
                System.out.println(transaction.toString());
                System.out.println(transaction.getAmount());
                System.out.println(transaction.getCreditor());
                System.out.println(transaction.getDebtor());
                System.out.println(cAcc.getId());
                System.out.println(transaction.getDescription());
            }
            System.out.println(acc.getTransactions());

        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
            System.out.println("whaaat");
        }

        return payment;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

}
