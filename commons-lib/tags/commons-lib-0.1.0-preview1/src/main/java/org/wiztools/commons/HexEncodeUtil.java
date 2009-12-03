package org.wiztools.commons;

/**
 * Utility method to convert byte array to hex-encoded string.
 * @author subwiz
 */
public final class HexEncodeUtil {

    final static String pseudo[] = {"0", "1", "2",
            "3", "4", "5", "6", "7", "8",
            "9", "a", "b", "c", "d", "e",
            "f"};

    private HexEncodeUtil(){}

    /**
     * This logic is based on the tip published by Jeff Boyle
     * here: http://www.devx.com/tips/Tip/13540
     * @param input byte[] input to convert
     * @return Hex representation of the input
     */
    public static String bytesToHex(byte input[]) {
        if (input == null || input.length <= 0) {
            return null;
        }

        final StringBuilder out = new StringBuilder(input.length * 2);

        for(int i=0; i<input.length; i++) {
            byte ch = (byte) (input[i] & 0xF0); // Strip off high nibble
            ch = (byte) (ch >>> 4);
            // shift the bits down
            ch = (byte) (ch & 0x0F);
            // must do this is high order bit is on!
            out.append(pseudo[(int) ch]); // convert the nibble to a String Character
            ch = (byte) (input[i] & 0x0F); // Strip off low nibble
            out.append(pseudo[(int) ch]); // convert the nibble to a String Character
        }

        return out.toString();
    }
}
