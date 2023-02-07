package com.pedro.common.util;

import com.pedro.auth.subject.api.PedroAuthUtil;
import com.pedro.common.enums.ServiceExceptionEnum;
import com.pedro.common.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 工具类
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    /**
     * 获取当前PedroAuth权限列表的第一个，并转换成数字
     */
    public static Integer getFirstRole() {
        List<String> roleList = PedroAuthUtil.getAuthSubject().getUser().getRoleList();
        if (roleList.isEmpty()) {
            logger.error("获取权限失败");
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }
        return Integer.parseInt(roleList.get(0));
    }

}
