package vn.vmg.ptdv.hola.account.core;

import lombok.Data;
import vn.vmg.ptdv.hola.common.des.Md5Util;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;

@Data
public class Password {
    private String clearText;
    private String encrypted;
    private String confirm;

    public Password withEncrypted(String password) throws SecurityDESException {
        this.encrypted = password;
        this.clearText = TripleDES.decrypt(KEY3DES, encrypted);
        return this;
    }

    public String getMd5Encrypted() {
        return Md5Util.md5(clearText);
    }

    public Password withConfirm(String confirmPassword) {
        this.confirm = confirmPassword;
        return this;
    }
}
