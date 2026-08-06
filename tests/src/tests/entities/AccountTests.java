package tests.entities;

import entities.ContaBancaria;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tests.factory.AccountFactory;

import static org.junit.jupiter.api.Assertions.*;
public class AccountTests {

    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount (){

        double amount = 200.0;

        double expectedValue = 196.0;

        ContaBancaria acc = new ContaBancaria("TH" , 0.0);

        acc.depositar(amount);

        Assertions.assertEquals(expectedValue, acc.getSaldo());


    }

    @Test
    public void depositShouldDoNothingWhenNegativeAmount(){

        double expectedValue = 100.0;
        ContaBancaria acc = AccountFactory.createAccount(expectedValue);
        double amount = -200.0;

        acc.depositar(amount);

        Assertions.assertEquals(expectedValue, acc.getSaldo());

    }

    @Test
    public void fullWithdrawShouldClearBalanceAndReturnFullBalance(){

        double expectedValue = 0.0;

        double initialBalance = 800.0;

        ContaBancaria acc = AccountFactory.createAccount(800);

        double result = acc.fullWithdraw();

        Assertions.assertTrue(expectedValue == acc.getSaldo());

        Assertions.assertTrue(result == initialBalance);
    }
}
