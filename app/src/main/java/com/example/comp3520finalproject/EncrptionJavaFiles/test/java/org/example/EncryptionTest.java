/*package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void testCipherLetters()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("ABC");
        assertEquals("FGH", result); // A→F B→G C→H (shift 5)
    }

    @Test
    void testCipherNums()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("123");
        assertEquals("678", result); // 1→6 2→7 3→8 (shift 5)
    }

    @Test
    void testCipherAlphanum()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("ABC123");
        assertEquals("FGH678", result);
    }

    @Test
    void testWrapAroundLetters()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("XYZ");
        assertEquals("CDE", result); // XYZ wrap around back to CDE with a shift of 5
    }

    @Test
    void testWrapAroundNums()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("789");
        assertEquals("234", result); // 789 wrap around to 234 with a shift of 5
    }

    @Test
    void testDecipher()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String encrypted = enc.caesarCipher("Halloween915");
        String decrypted = enc.caesarDecipher(encrypted);
        assertEquals("Halloween915", decrypted); // decipher code returns the initial input
    }

    @Test
    void testSpecialCharRemainSame()
    {
        ParkingEncryption enc = new ParkingEncryption();
        String result = enc.caesarCipher("Holidays1!");
        assertEquals("Mtqnifdx6!", result);
    }
}*/
