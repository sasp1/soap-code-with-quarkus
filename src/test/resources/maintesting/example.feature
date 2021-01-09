Feature: Payment

  Scenario: If customer id was not found
    Given Customer id "123" that did not exist
    And a merchant with id "mid1" and cpr number "123132-1232"
    When the merchant initiates a payment for 10 kr by the customer
    Then The payment was unsuccessful

  Scenario: If merchant id was not found
    Given a customer with id "cid1" and cpr number "010101-2002"
    And a merchant with id "123" that did not exist
    When the merchant initiates a payment for 10 kr by the customer
    Then The payment was unsuccessful

  Scenario: Successful Payment
    Given the customer "Ryan" "Anderson" with CPR "011194-7124" has a bank account with balance 1000
    And the customer is registered with DTUPay
    And the merchant "Jo" "Kuckles" with CPR number "110561-2231" has a bank account with balance 2000
    And the merchant is registered with DTUPay
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is successful
    And the balance of the customer at the bank is 990 kr
    And the balance of the merchant at the bank is 2010

  Scenario: Unsuccessful payment if customer doesn't have bank account
    Given the customer "Ryan" "Anderson" with CPR "011194-7124" does not have a bank account with balance 1000
    And the customer is registered with DTUPay
    And the merchant "Jo" "Kuckles" with CPR number "110561-2231" has a bank account with balance 2000
    And the merchant is registered with DTUPay
    When the merchant initiates a payment for "10" kr by the customer
    Then the payment is successfulAnd the balance of the customer at the bank is 990 kr
    And the balance of the merchant at the bank is 2010