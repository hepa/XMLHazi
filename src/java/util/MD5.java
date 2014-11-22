package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {

    public static String Convert(String in) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(in.getBytes(), 0, in.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }
}
