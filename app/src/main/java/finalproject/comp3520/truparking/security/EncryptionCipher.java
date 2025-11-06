package finalproject.comp3520.truparking.security;

/**
 * This class creates some encryption methods for the Parking App
 */

public class EncryptionCipher
{
    // This is the fixed shift amount for simplicity instead of choosing the shift each time
    private static final int SHIFT = 5;
    /**
     * The Caesar Cipher will shift the inputs by the determined amount
     * It works for both alphabetical and numerical characters
     * Non-alphanumerical characters will stay the same
     *
     * @param input: The input string that will be encrypted
     * @return The transformed input after the ciphering (null if the input was null) returned as a string
     */
    public static String caesarCipher(String input)
    {
        if (input == null)
        {
            return null;
        }

        String result = "";

        for (char c : input.toCharArray())
        {
            if (c >= 'A' && c <= 'Z')
            {
                char shiftedChar = (char) (c + SHIFT);
                if (shiftedChar > 'Z')
                {
                    shiftedChar -= 26;
                }
                result += shiftedChar;
            }

            else if (c >= 'a' && c <= 'z')
            {
                char shiftedChar = (char) (c + SHIFT);
                if (shiftedChar > 'z')
                {
                    shiftedChar -= 26;
                }
                result += shiftedChar;
            }

            else if (c >= '0' && c <= '9')
            {
                char shiftedChar = (char) (c + SHIFT);
                if (shiftedChar > '9')
                {
                    shiftedChar -= 10;
                }
                result += shiftedChar;
            }
            else
            {
                result += c;
            }
        }
        return result;
    }

    /**
     * Decryption method for the Caesar Cipher
     *
     * @param input: This is the encrypted message (will return null if it is Null)
     * @return The decrypted input is returned as a string
     */
    public static String caesarDecipher(String input)
    {
            if (input == null)
            {
                return null;
            }

            String result = "";

            for (char c : input.toCharArray())
            {
                if (c >= 'A' && c <= 'Z') {
                    char shiftedChar = (char)(c - SHIFT);
                    if (shiftedChar < 'A')
                    {
                        shiftedChar += 26;
                    }
                    result += shiftedChar;

                } else if (c >= 'a' && c <= 'z') {
                    char shiftedChar = (char)(c - SHIFT);
                    if (shiftedChar < 'a') shiftedChar += 26;
                    result += shiftedChar;

                } else if (c >= '0' && c <= '9') {
                    char shiftedChar = (char)(c - SHIFT);
                    if (shiftedChar < '0') shiftedChar += 10;
                    result += shiftedChar;

                } else {
                    result += c;
                }
            }

            return result;
    }
}
