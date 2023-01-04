package com.pedro.domain.support.encryption;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 */
public class EncryptionUtil {

    /**
     * 加密
     *
     * @param username
     * @param password
     * @param salt
     * @param hashTimes
     * @return
     */
    public static String encryptPassword(String username, String password, String salt, int hashTimes) {
        // 1.利用传入的信息进行加密，得到密文
        Md5Hash md5Hash = new Md5Hash(password, username + salt, hashTimes);
        // 2.返回密文
        return md5Hash.toString();
    }

    /**
     * 获取盐值
     *
     * @return
     */
    public static String getSalt() {
        return Long.toString(System.currentTimeMillis());
    }
}
