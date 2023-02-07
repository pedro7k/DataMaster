package com.pedro.domain.support.encryption;


import com.pedro.auth.common.enums.EncryptionEnum;
import com.pedro.auth.subject.api.PedroAuthUtil;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;

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
     * @return
     */
    public static String encryptPassword(String username, String password, String salt) {
        // 1.利用传入的信息进行加密，得到密文
        String encodedPassword = null;
        try {
            encodedPassword = com.pedro.auth.util.EncryptionUtil.encode(username, password, salt, EncryptionEnum.MD5_ENCRYPTION);
        } catch (Throwable e) {
            throw new ServiceException(ServiceExceptionEnum.ENCODE_PASSWORD_ERROR);
        }
        // 2.返回密文
        return encodedPassword;
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
