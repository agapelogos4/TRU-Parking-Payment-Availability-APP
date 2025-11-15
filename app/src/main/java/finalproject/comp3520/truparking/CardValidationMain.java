package org.example;
// Created By Armaan Bains T00711438
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        CardPaymentValidation validate = new CardPaymentValidation();

        System.out.print("Enter credit card number: ");
        String cardNum = scan.nextLine().trim();


        System.out.println(validate.checkCardInputError(cardNum));
    }
}