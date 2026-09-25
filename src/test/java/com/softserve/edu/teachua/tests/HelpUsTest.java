package com.softserve.edu.teachua.tests;

import org.junit.jupiter.api.Test;

public class HelpUsTest extends TestRunner {

    @Test
    void payButtonIsDisabledUntilRequiredFieldsAreFilled() {
        // Task 4. Open Help Us from the footer link "Допомогти проекту".
        // Assert the pay action stays unavailable until the form allows it.
        throw new UnsupportedOperationException("Task 4: check the disabled pay action");
    }

    @Test
    void alternativePaymentMethodsArePresent() {
        // Task 4. From the home page open Help Us, then assert every method
        // shown in img/payment_methods.png: Google Pay, card brands,
        // "Інший спосіб оплати", Privat24, cash terminal, MasterPass, Visa Checkout.
        throw new UnsupportedOperationException("Task 4: check payment methods");
    }
}
