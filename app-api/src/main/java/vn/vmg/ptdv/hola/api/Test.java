package vn.vmg.ptdv.hola.api;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        System.out.println(OffsetDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(System.nanoTime());
    }
}
