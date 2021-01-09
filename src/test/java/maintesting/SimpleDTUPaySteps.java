package maintesting;

import dtu.ws.fastmoney.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class SimpleDTUPaySteps {
    String cid, mid;
    SimpleDTUPayService dtuPayService = new SimpleDTUPayService();
    boolean successful;
    String cAccountId;
    private String mAccountId;
    BankService bankService = new BankServiceService().getBankServicePort();
    private String cprNumber;
    private String mCprNumber;

    @Given("a customer with id {string} and cpr number {string}")
    public void aCustomerWithId(String cid, String cprNumber) {
        this.cid = cid;
        this.cprNumber = cprNumber;
    }

    @Given("a merchant with id {string} and cpr number {string}")
    public void aMerchantWithId(String mid, String cprNumber) {
//        dtuPay.addMerchant(mid);
        this.mid = mid;
        this.mCprNumber = cprNumber;
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int amount) {
        System.out.println(cprNumber);
        successful = dtuPayService.pay(amount, cid, mid, cprNumber, mCprNumber);
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertTrue(successful);
    }


    @Given("Customer id {string} that did not exist")
    public void customerIdThatDidNotExist(String cid) {
        this.cid = cid;
    }


    @And("a merchant with id {string} that did exist")
    public void aMerchantWithIdThatDidExist(String mid) {
        this.mid = mid;
//        dtuPay.addMerchant(mid);
    }

    @When("the merchant initiates a payment for {int} kr by the non-existing customer")
    public void theMerchantInitiatesAPaymentForKrByTheNonExistingCustomer(int amount) {
        successful = dtuPayService.pay(amount, cid, mid, cprNumber, mCprNumber);
    }

    @Then("The payment was unsuccessful")
    public void thePaymentWasUnsuccessful() {
        assertFalse(successful);
    }

    @And("a merchant with id {string} that did not exist")
    public void aMerchantWithIdThatDidNotExist(String mid) {
        this.mid = mid;
    }

    @Given("the customer {string} {string} with CPR {string} has a bank account with balance {int}")
    public void theCustomerWithCPRHasABankAccount(String firstName, String lastName, String cprNumber, int balance) {
        var user = new User();
        user.setCprNumber(cprNumber);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        this.cprNumber = cprNumber;
        cid = "cid1";

        try {
            cAccountId = bankService.createAccountWithBalance(user, BigDecimal.valueOf(balance));
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @And("the customer is registered with DTUPay")
    public void theCustomerIsRegisteredWithDTUPay() {
        dtuPayService.addCustomer(new Customer(cid, cprNumber));
    }

    @And("the merchant {string} {string} with CPR number {string} has a bank account with balance {int}")
    public void theMerchantWithCPRNumberHasABankAccount(String firstName, String lastName, String cprNumber, int balance) {
        var user = new User();
        user.setCprNumber(cprNumber);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        mid = "mid1";
        this.mCprNumber = cprNumber;

        try {
            mAccountId = bankService.createAccountWithBalance(user, BigDecimal.valueOf(balance));
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @And("the merchant is registered with DTUPay")
    public void theMerchantIsRegisteredWithDTUPay() {
        dtuPayService.addMerchant(new Merchant(mid, cprNumber));
    }

    @And("the balance of the merchant at the bank is {int}")
    public void theBalanceOfTheMerchantAtTheBankIs(int amount) {
        try {
            Account account = bankService.getAccount(mAccountId);
            assertEquals(amount, account.getBalance().intValue());
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @And("the balance of the customer at the bank is {int} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(int balance) {
        try {
            Account account = bankService.getAccount(cAccountId);
            assertEquals(balance, account.getBalance().intValue());
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            bankService.retireAccount(cAccountId);
            bankService.retireAccount(mAccountId);
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }

    }


}
