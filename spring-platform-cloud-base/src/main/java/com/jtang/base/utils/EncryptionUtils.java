package com.jtang.base.utils;

import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密算法
 * @author linjt
 * @date 2020/7/7
 */
public class EncryptionUtils {

    public static String getPBKDF2Code(String password, String salt) {
        // 迭代次数，可依情况修改
        int interNum = 10000;
        //128位16进制字符
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), interNum, 128 * 4);
        // PBKDF2算法应用很广泛，大多数数据库支持该算法，可用于到处密钥，也可用于口令保存
        try {
            // SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  使用PBKDF2WithHmacSHA1
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] bytes = secretKeyFactory.generateSecret(spec).getEncoded();
            StringBuilder builder = new StringBuilder();
            for (byte num : bytes) {
                builder.append(Integer.toString((num & 0xff) + 0x100, 16).substring(1));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("algorithm wrong");
            return null;
        }
    }

    /** MD5 加密 */
    public String MD5(String sourceStr){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte value : b) {
                i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5加密失败");
        }
        return result;
    }

    /** 获取盐值 */
    public static String getSalt(){
        return UIDUtils.getUUID().substring(0, 13);
    }


}
