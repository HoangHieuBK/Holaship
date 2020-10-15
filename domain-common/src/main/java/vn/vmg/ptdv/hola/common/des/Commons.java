package vn.vmg.ptdv.hola.common.des;

import org.apache.commons.codec.binary.Hex;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Commons {


    private static final String FORMATTER_UNIQUE_REQUEST = "yyyyMMddHHmmssSSS";
    private static final String FORMATTER_UNIQUE_REQUEST_YMD = "yyyyMMdd";

    public static String bytesToHex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    public static byte[] hexToBytes(String hexString) {
        try {
            if (hexString != null)
                return Hex.decodeHex(hexString.toCharArray());
        } catch (Exception ex) {
        }
        return null;
    }

    public static boolean isGreaterThan(String value, int maxlength) {
        return (value.length() > maxlength);
    }

    public static boolean isSmallerThan(String value, int minLength) {
        return (value.length() < minLength);
    }

    public static Date convertStringToDate(String strDate) {
        LocalDate localDate = LocalDate.parse(strDate, DateTimeFormatter.BASIC_ISO_DATE);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String genRequestTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_UNIQUE_REQUEST);
        ZonedDateTime zdt = ZonedDateTime.now();
        zdt.plusNanos((long) (Math.random() * 5));
        String zdtString = zdt.format(formatter);
        return zdtString;
    }

    public static String genRequestTimeYMD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_UNIQUE_REQUEST_YMD);
        ZonedDateTime zdt = ZonedDateTime.now();
        zdt.plusNanos((long) (Math.random() * 5));
        String zdtString = zdt.format(formatter);
        return zdtString;
    }

    public static String genRequestTimeNoUniqueId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_UNIQUE_REQUEST);
        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtString = zdt.format(formatter);
        return zdtString;
    }
}
