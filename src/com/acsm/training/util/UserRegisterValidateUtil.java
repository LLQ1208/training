package com.acsm.training.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 13:12 2017/11/27
 */
public class UserRegisterValidateUtil {

    private static final Logger logger = Logger.getLogger(UserRegisterValidateUtil.class);

    public static String encodePassword(String password, String algorithm) {

        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            logger.error("encode password !", e);
            return password;
        }
        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

}
