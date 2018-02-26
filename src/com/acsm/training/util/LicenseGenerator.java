package com.acsm.training.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class LicenseGenerator {
    /** Logger日志 */
    Logger logger = Logger.getLogger(LicenseGenerator.class);

    private String priKey;
    private String pubKey;

    /**
     * 生成公钥、私钥对
     * @param seed 种子字符串，这里使用的是公司名
     * @return
     * @throws Exception
     */
    public String[] generater(String seed) throws Exception {
        try {
            java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator
                    .getInstance("RSA");
            //通过种子字符串生成SecureRandom
            SecureRandom secrand = new SecureRandom();
            secrand.setSeed(seed.getBytes()); // 初始化随机产生器
            // 初始加密，512位已被破解，用1024位,最好用2048位
            keygen.initialize(1024, secrand);
            KeyPair keys = keygen.genKeyPair();//公钥私钥对
            //公钥
            PublicKey pubkey = keys.getPublic();
            //私钥
            PrivateKey prikey = keys.getPrivate();
            /**
             * 使用BASE64加密算法:
             * Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式
             * Base64编码要求把3个8位字节（3*8=24）转化为4个6位的字节（4*6=24），
             * 之后在6位的前面补两个0，形成8位一个字节的形式。
             */
            pubKey = new BASE64Encoder().encode(pubkey.getEncoded());
            priKey = new BASE64Encoder().encode(prikey.getEncoded());

            System.out.println("公钥是：" + pubKey);
            System.out.println("私钥是：" + priKey);
            //返回公钥私钥对
            return new String[] { pubKey, priKey };
        }
        catch (Exception e) {
            logger.error("生成密钥对失败", e);
        }

        return null;
    }

    /**
     * 生成注册码
     * @param priKeyText 私钥
     * @param boardSN 种子字符串，这里是机器码
     * @param validToDate 验证截止日期
     * @return
     */
    public String sign(String priKeyText,String boardSN,String validToDate) {
        try {
        	//编码
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(priKeyText));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey prikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名，使用MD5withRSA加密方式
            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(prikey);
            signet.update(boardSN.getBytes());
            String signed = new BASE64Encoder().encode(signet.sign());
            //将产品使用截止日期编码
            String validToDateEncrypt = new BASE64Encoder().encode(validToDate.getBytes());
            signed += validToDateEncrypt;
            //返回注册码
            return signed;
        }
        catch (Exception e) {
            logger.error("签名失败", e);
        }
        return null;
    }

    public static void main(String[] args) {
        LicenseGenerator generator = new LicenseGenerator();
        //种子字符串，主板序列号
        String boardSN = HardWareUtils.getMotherboardSN();
        String validToDate = "2015-02-29";
        try {
            // 生成公钥和私钥
            String[] keyPairs = new LicenseGenerator().generater(boardSN);
            /**
             * 生成注册码
             * keyPairs[1]：私钥
             * plainText：种子字符串
             * edition：产品版本
             */
            String signed = generator.sign(keyPairs[1], boardSN, validToDate);
            System.out.println("注册码为：" + signed);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
