package com.pedro.domain.dbProcess.service.tableCreation;

import com.pedro.domain.dbProcess.model.req.TableCreationReq;

public interface VisualCreateTableService {

    /**
     * 可视化创建表
     */
    void createTable(TableCreationReq req);
}
