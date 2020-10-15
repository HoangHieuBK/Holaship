package vn.vmg.ptdv.hola.common.des;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static vn.vmg.ptdv.hola.common.des.Commons.bytesToHex;
import static vn.vmg.ptdv.hola.common.des.Commons.hexToBytes;

public class TripleDES {

    final static String HARD_KEY = "thiSISatempkey";

    public static String Encrypt(String plainText, String key) {
        try {
            byte[] arrayBytes = hexToBytes(key);
            // getValidKey(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.ENCRYPT_MODE, seckey);
            byte[] plainByte = plainText.getBytes("UTF8");
            byte[] encryptedByte = cipher.doFinal(plainByte);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(encryptedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ham giai du lieu tu client dang web
     *
     * @param key
     * @param cipher
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchProviderException
     */
    public static String decrypt(String key, String cipher) throws SecurityDESException {
        try {
            byte[] bytes = null;

            SecretKey sKey = null;
            Security.addProvider(new BouncyCastleProvider());
            Cipher encipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
            bytes = hexToBytes(cipher);
            // bytes=Base64Utils.base64Decode(cipher);
            // bytes = hexToBytes(cipher);
            sKey = getKey(key);
            // Encrypt
            byte[] enc;
            encipher.init(Cipher.DECRYPT_MODE, sKey);
            enc = encipher.doFinal(bytes);
            String returnStr = new String(enc, "UTF-8");
            System.out.println("DU LIEU SAU GIAI MA=" + returnStr);
            return returnStr;
        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException |
                NoSuchProviderException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            throw new SecurityDESException(e.getCause());
        }
    }

    /**
     * Ham encrypt du lieu tu client dang web
     *
     * @param key
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     */
    public static String encrypt(String key, String input) throws SecurityDESException {
        try {
            byte[] bytes = null;
            SecretKey sKey = null;
            Security.addProvider(new BouncyCastleProvider());
            Cipher encipher = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
            bytes = input.getBytes("UTF-8");
            sKey = getKey(key);
            // Encrypt
            byte[] enc;
            encipher.init(Cipher.ENCRYPT_MODE, sKey);
            enc = encipher.doFinal(bytes);
            return bytesToHex(enc);
        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException |
                NoSuchProviderException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            throw new SecurityDESException(e.getCause());
        }
    }

    private static SecretKey getKey(String key) {
        // key=TripleDESEncryption.md5(key);
        key = key.substring(0, 24);
        byte[] bKey = key.getBytes();
        try {
            DESedeKeySpec keyspec = new DESedeKeySpec(bKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

            SecretKey lclSK = keyFactory.generateSecret(keyspec);

            return lclSK;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    public static String EncryptHex(String plainText, String key) {
        try {
            byte[] arrayBytes = getValidKey(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.ENCRYPT_MODE, seckey);
            byte[] plainByte = plainText.getBytes("UTF8");
            byte[] encryptedByte = cipher.doFinal(plainByte);
            return bytesToHex(encryptedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String EncryptHexHexKey(String plainText, String key) {
        try {

            byte[] arrayBytes = hexToBytes(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.ENCRYPT_MODE, seckey);
            byte[] plainByte = plainText.getBytes("UTF8");
            byte[] encryptedByte = cipher.doFinal(plainByte);
            return bytesToHex(encryptedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Decrypt(String encryptData, String key) {
        try {
            byte[] arrayBytes = getValidKey(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.DECRYPT_MODE, seckey);
            BASE64Decoder decode = new BASE64Decoder();
            byte[] encryptByte = decode.decodeBuffer(encryptData);
            byte[] plainByte = cipher.doFinal(encryptByte);
            return new String(plainByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DecryptHexHexKey(String encryptData, String key) {
        try {
            byte[] arrayBytes = hexToBytes(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.DECRYPT_MODE, seckey);
            // BASE64Decoder decode = new BASE64Decoder();
            byte[] encryptByte = hexToBytes(encryptData);
            byte[] plainByte = cipher.doFinal(encryptByte);
            String plainText = new String(plainByte);

            return plainText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] DecryptHexReturnByte(String encryptData, String key) {
        byte[] plainByte = null;
        try {
            byte[] arrayBytes = getValidKey(key);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            SecretKey seckey = skf.generateSecret(ks);
            //
            cipher.init(Cipher.DECRYPT_MODE, seckey);
            // BASE64Decoder decode = new BASE64Decoder();

            byte[] encryptByte = hexToBytes(encryptData);
            plainByte = cipher.doFinal(encryptByte);
            return plainByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getValidKey(String key) {
        try {
            String sTemp = Md5Util.md5(key).substring(0, 24);
            return sTemp.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptHardKey(String encryptData) {
        return Decrypt(encryptData, HARD_KEY);
    }

    public static String generateHexKey() {
        // Get a key generator for Triple DES (a.k.a DESede)
        KeyGenerator keygen;

        // 676b8a1085cdb3ec20629d5731bf8ff7
        // 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
        try {
            keygen = KeyGenerator.getInstance("DESede");
//			keygen.init(168);
            keygen.init(112);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            DESedeKeySpec keyspec;
            try {
                keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
                byte[] rawkey = keyspec.getKey();

                return bytesToHex(rawkey);
                // return bytesToHex(rawkey);
            } catch (InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        // Use it to generate a key
    }

    public static String generateBase64StringKey() {
        // Get a key generator for Triple DES (a.k.a DESede)
        KeyGenerator keygen;

        // 676b8a1085cdb3ec20629d5731bf8ff7
        // 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
        try {
            keygen = KeyGenerator.getInstance("DESede");
            keygen.init(168);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            DESedeKeySpec keyspec;
            try {
                keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
                byte[] rawkey = keyspec.getKey();

                return Base64.encodeBase64String(rawkey);// Base64Utils.base64Encode(rawkey);
                // return bytesToHex(rawkey);
            } catch (InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        // Use it to generate a key
    }

    public static String generateBase64StringKeyDes() {
        // Get a key generator for Triple DES (a.k.a DESede)
        KeyGenerator keygen;

        // 676b8a1085cdb3ec20629d5731bf8ff7
        // 7ff7269eb0f44c3bd5eaf8523d49975b385df4abaed54367
        try {
            keygen = KeyGenerator.getInstance("DES");
            keygen.init(168);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
            DESedeKeySpec keyspec;
            try {
                keyspec = (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(), DESedeKeySpec.class);
                byte[] rawkey = keyspec.getKey();

                return Base64.encodeBase64String(rawkey); //Base64Utils.base64Encode(rawkey);
                // return bytesToHex(rawkey);
            } catch (InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        // Use it to generate a key
    }
}