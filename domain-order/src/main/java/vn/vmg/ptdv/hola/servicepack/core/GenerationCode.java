package vn.vmg.ptdv.hola.servicepack.core;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerationCode {
    public static String generationServicePack() {
        String code = "HOLAGN_";
        Date date = new Date();
        Format formatter = new SimpleDateFormat("ddMMyy");
        code = code + formatter.format(date);
        return code;
    }
}
