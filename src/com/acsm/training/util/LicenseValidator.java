package com.acsm.training.util;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

public class LicenseValidator {

    private static Logger log = Logger.getLogger(LicenseValidator.class);

    /** 公钥 */
    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd+Tk/20U6ofMKQWBSID+M84P0R3vB9pKh8bGs"
    			+"jcWkVzBDO2uoLuVux4Mk4bhtLvfZgToNjtCvsOE6z1bMHILZe0D/JIn7Q0+lqc6ewQllD8ncVTNp"
    			+"cs5q7Ud5bXRcp00wISm2IerldYipum/TECsE4vCr6zdC5/ao9WT12FVTGQIDAQAB";
    /** 软件版本 */
    public static String edition;
    /** 软件是否合法的标志 */
    public static boolean validFlag;

    /** 错误类型 1：未授权 2：已过期 */
    public static int errorType = 0;

    public static boolean verify(String pubKeyText, String plainText,
            String signText) {
        try {
            // 解密由base64编码的公钥,并构造X509EncodedKeySpec对象
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(pubKeyText));
            // RSA对称加密算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            // 解密由base64编码的数字签名
            byte[] signed = new BASE64Decoder().decodeBuffer(signText);
            Signature signatureChecker = Signature.getInstance("MD5withRSA");
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(plainText.getBytes());
            // 验证签名是否正常
            if (signatureChecker.verify(signed))
                return true;
            else
                return false;
        }
        catch (Throwable e) {
            System.out.println("校验签名失败。");
            e.printStackTrace();
            return false;
        }
    }

    public static String parseEdition(String snKey) {
        if (snKey == null || snKey.isEmpty()) {
            return "";
        }
        String edition = "";
        try {
            edition = new String(new BASE64Decoder().decodeBuffer(snKey
                    .substring(88, 92)));
        }
        catch (IOException e) {
            log.error("IOException occurred", e);
        }
        return edition;
    }

    public static String parseValidToDate(String snKey) {
        if (snKey == null || snKey.isEmpty()) {
            return "";
        }
        String validToDate = "";
        try {
            validToDate = new String(new BASE64Decoder().decodeBuffer(snKey
                    .substring(92)));
        }
        catch (IOException e) {
            log.error("IOException occurred", e);
        }

        return validToDate;
    }

    public static String getEdition() {
        return edition;
    }

    public static void saveEdition(String edition) {
        LicenseValidator.edition = edition;
    }

    public static void saveValidFlag(boolean validFlag) {
        LicenseValidator.validFlag = validFlag;
    }

    public static boolean getValidFlag() {
        return validFlag;
    }

    public static void setValidFlag(boolean validFlag) {
        LicenseValidator.validFlag = validFlag;
    }

    public static String getPublickey() {
        return publicKey;
    }

    public static int getErrorType() {
        return errorType;
    }

    public static void setErrorType(int errorType) {
        LicenseValidator.errorType = errorType;
    }

    public static void main(String[] args) throws Exception {
        String snKey = PropertyUtil.getProperty("licenseKey");
        boolean result = verify(publicKey,
                PropertyUtil.getProperty("Organisation"),
                snKey.substring(0, 88));
        System.out.println(parseEdition(snKey));
        System.out.println(parseValidToDate(snKey));
        System.out.println(result);
    }
}
