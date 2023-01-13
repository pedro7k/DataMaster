package com.pedro.domain.dbProcess.repository;

import com.pedro.domain.dbProcess.model.vo.TableCreationVO;

public interface TableCreationRepository {

    /**
     * 创建表
     */
    void createTable(TableCreationVO tableCreationVO);
}
