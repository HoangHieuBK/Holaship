package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class Gender {
    public final static String MALE = "MALE";
    public final static String FEMALE = "FEMALE";
    public final static String UNDEFINED = "UNDEFINED";

    public final static Integer MALE_VALUE = 1;
    public final static Integer FEMALE_VALUE = 2;
    public final static Integer UNDEFINED_VALUE = 3;
    private Integer value;
    private String text;

    public Gender(Integer value) {
        this.value = value;
    }

    public Gender() {

    }

    public String display() {
        if (value == 1) {
            text = MALE;
        } else if (value == 2) {
            text = FEMALE;
        } else {
            text = UNDEFINED;
        }
        return text;
    }

    public Gender fromText(String gender) {
        if (MALE.contains(gender)) {
            this.value = MALE_VALUE;
        } else if (FEMALE.contains(gender)) {
            this.value = FEMALE_VALUE;
        } else {
            this.value = UNDEFINED_VALUE;
        }
        return this;
    }
}
