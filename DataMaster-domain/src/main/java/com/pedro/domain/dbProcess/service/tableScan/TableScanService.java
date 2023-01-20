package com.pedro.domain.dbProcess.service.tableScan;

import com.pedro.domain.dbProcess.model.res.TableScanRes;
import com.pedro.domain.dbProcess.model.vo.TableScanFreqVO;

import java.util.List;

/**
 * 单表扫描服务
 */
public interface TableScanService {

    /**
     * 单表扫描方法
     */
    List<TableScanRes> scanTable(int tid);

    /**
     * 获取tid和扫描频率，供定时任务使用
     */
    List<TableScanFreqVO> queryAllTableScanFreq();

}
