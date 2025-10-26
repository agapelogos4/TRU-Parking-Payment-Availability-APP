package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to encrypt:");
        String input = scanner.nextLine();

        // ✅ Call methods directly because they are STATIC
        String encrypted = ParkingEncryption.caesarCipher(input);
        String decrypted = ParkingEncryption.caesarDecipher(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}