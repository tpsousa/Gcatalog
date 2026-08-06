package tests.factory;

import entities.ContaBancaria;

public class AccountFactory {

    public static ContaBancaria createEmptyAccount(){

        return new ContaBancaria("",0.0);

    }

    public static ContaBancaria createAccount(double initialBalance){

        return new ContaBancaria("", initialBalance);

    }
}
