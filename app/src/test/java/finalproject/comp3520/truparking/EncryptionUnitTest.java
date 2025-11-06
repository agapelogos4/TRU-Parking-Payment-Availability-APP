package finalproject.comp3520.truparking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import finalproject.comp3520.truparking.security.EncryptionCipher;

public class EncryptionUnitTest {

    @Test
    public void testCipherLetters()
    {
        String result = EncryptionCipher.caesarCipher("ABC");
        assertEquals("FGH", result); // A→F B→G C→H (shift 5)
    }

    @Test
    public void testCipherNums()
    {
        String result = EncryptionCipher.caesarCipher("123");
        assertEquals("678", result); // 1→6 2→7 3→8 (shift 5)
    }

    @Test
    public void testCipherAlphanum()
    {
        String result = EncryptionCipher.caesarCipher("ABC123");
        assertEquals("FGH678", result);
    }

    @Test
    public void testWrapAroundLetters()
    {
        String result = EncryptionCipher.caesarCipher("XYZ");
        assertEquals("CDE", result); // XYZ wrap around back to CDE with a shift of 5
    }

    @Test
    public void testWrapAroundNums()
    {
        String result = EncryptionCipher.caesarCipher("789");
        assertEquals("234", result); // 789 wrap around to 234 with a shift of 5
    }

    @Test
    public void testDecipher()
    {
        String encrypted = EncryptionCipher.caesarCipher("Halloween915");
        String decrypted = EncryptionCipher.caesarDecipher(encrypted);
        assertEquals("Halloween915", decrypted); // decipher code returns the initial input
    }

    @Test
    public void testSpecialCharRemainSame()
    {
        String result = EncryptionCipher.caesarCipher("Holidays1!");
        assertEquals("Mtqnifdx6!", result);
    }
}
