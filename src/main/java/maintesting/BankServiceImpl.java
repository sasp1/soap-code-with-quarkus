package maintesting;

import dtu.ws.fastmoney.*;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;

@WebService(endpointInterface = "dtu.ws.fastmoney.BankService")
public class BankServiceImpl implements BankService {
    BankServiceService bankService = new BankServiceService();



    @Override
    public Account getAccount(String accountId) throws BankServiceException_Exception {
        GetAccount getAccount = new ObjectFactory().createGetAccount();


        return null;
    }

    @Override
    public Account getAccountByCprNumber(String cpr) throws BankServiceException_Exception {
        return null;

    }

    @Override
    public String createAccountWithBalance(User user, BigDecimal balance) throws BankServiceException_Exception {
        return null;
    }

    @Override
    public void retireAccount(String accountId) throws BankServiceException_Exception {

    }

    @Override
    public List<AccountInfo> getAccounts() {
        GetAccountsResponse getAccounts = new ObjectFactory().createGetAccountsResponse();
        System.out.println(getAccounts.getReturn());
        return null;

    }

    @Override
    public void transferMoneyFromTo(String debtor, String creditor, BigDecimal amount, String description) throws BankServiceException_Exception {

    }
}
