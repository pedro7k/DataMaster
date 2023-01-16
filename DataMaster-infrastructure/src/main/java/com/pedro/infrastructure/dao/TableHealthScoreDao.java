package com.pedro.infrastructure.dao;

import com.pedro.domain.dbProcess.model.vo.TableHealthScoreVO;
import com.pedro.infrastructure.po.ScoreLinePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableHealthScoreDao {

    /**
     * 根据tid查询某表当前健康分
     */
    Double queryCurrentTableHealthScoreByTid(int tid);

    /**
     * 通过tid删除关于某表的记录
     */
    void deleteRecordByTid(int tid);

    /**
     * 插入一条单表健康分数据
     */
    void insertTableHealthScore(TableHealthScoreVO tableHealthScoreVO);

    /**
     * 获取过去七日健康分变化趋势
     */
    List<ScoreLinePO> query7DaysTableHealthScoreLine(int tid);
}
