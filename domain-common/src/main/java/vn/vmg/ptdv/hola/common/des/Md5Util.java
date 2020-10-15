package vn.vmg.ptdv.hola.common.des;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static MessageDigest sMd5MessageDigest;
    private static StringBuilder sStringBuilder;

    static {
        try {
            sMd5MessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        sStringBuilder = new StringBuilder();
    }

    private Md5Util() {
    }

    /**
     * Return a hash according to the MD5 algorithm of the given String.
     *
     * @param s The String whose hash is required
     * @return The MD5 hash of the given String
     */
    public static String md5(String s) {

        sMd5MessageDigest.reset();
        try {
            sMd5MessageDigest.update(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        byte digest[] = sMd5MessageDigest.digest();

        sStringBuilder.setLength(0);
        for (int i = 0; i < digest.length; i++) {
            final int b = digest[i] & 255;
            if (b < 16) {
                sStringBuilder.append('0');
            }
            sStringBuilder.append(Integer.toHexString(b));
        }

        return sStringBuilder.toString();
    }
}