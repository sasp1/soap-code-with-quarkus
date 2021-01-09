package maintesting;

public class Payment {

    private int amount;
    private Customer customer;
    private Merchant merchant;


    public Payment(){

    }

    public Payment(int amount, Customer customer, Merchant merchant) {
        this.amount = amount;
        this.customer = customer;
        this.merchant = merchant;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
