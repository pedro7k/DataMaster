package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.TableDetailVO;
import com.pedro.domain.dbProcess.model.vo.TableRuleVO;
import com.pedro.infrastructure.po.TableRulePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableRuleDao {

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 插入表rule
     */
    void insertTableRule(TableRuleVO tableRuleVO);

    /**
     * 通过cid查询相关联的rules
     */
    List<TableRulePO> queryTableRuleByCid(int cid);
}
