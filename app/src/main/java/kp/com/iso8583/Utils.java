package kp.com.iso8583;

/**
 * Created by Krishnaprasad.n on 4/13/2016.
 */
public class Utils {

    public static String hexStringToNBitBinary(String hexString, int N) {
        long decimalResult = 0;
        int length = hexString.length(); //store the length in memory, for devices without a JIT
        int i;

        for (i = 0; i < length; i++) {
            //equivalent to multiplying the result by 16 and adding the value of the new digit, but uses bit operations for performance
            decimalResult = (decimalResult << 4) | Character.digit(hexString.charAt(i), 16);
        }
        String binary = Long.toBinaryString(decimalResult); //gives us a binary string, but is missing leading zeros
        length = binary.length();
        if (length == N) {
            return binary;
        } else if (length < N) {
            int difference = N - length;
            char[] buffer = new char[N]; //allocate a new char buffer of the desired length
            for (i = 0; i < difference; i++) {
                buffer[i] = '0'; //fill in the needed number of leading zeros
            }
            binary.getChars(0, length, buffer, difference); //copies the original binary string into the buffer after leading zeros
            return new String(buffer);
        } else {
            throw new IllegalArgumentException("Hex String is not a N bit number!");
        }
    }
}
