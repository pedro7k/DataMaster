package com.pedro.infrastructure.repository;

import com.pedro.domain.form.model.vo.TableDetailFormVO;
import com.pedro.domain.form.repository.TableDetailFormRepository;
import com.pedro.infrastructure.dao.TableDetailsDao;
import com.pedro.infrastructure.dao.TableRuleDao;
import com.pedro.infrastructure.po.TableDetailPO;
import com.pedro.infrastructure.po.TableRulePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableDetailFormRepositoryImpl implements TableDetailFormRepository {

    @Resource
    private TableDetailsDao tableDetailsDao;

    @Resource
    private TableRuleDao tableRuleDao;

    @Override
    public List<TableDetailFormVO> loadTableDetailForm(int tid) {

        // 1.获取数据
        List<TableDetailPO> tableDetailPOList = tableDetailsDao.queryTableDetailByTid(tid);

        // 2.转换数据为VO
        List<TableDetailFormVO> tableDetailFormVOList = new ArrayList<>();
        for (TableDetailPO tableDetailPO : tableDetailPOList) {
            TableDetailFormVO tableDetailFormVO = new TableDetailFormVO();
            tableDetailFormVO.setCid(tableDetailPO.getCid());
            tableDetailFormVO.setColumnName(tableDetailPO.getColumnName());
            tableDetailFormVO.setDataType(tableDetailPO.getDataType());
            tableDetailFormVO.setIfPrimary(tableDetailPO.isIfPrimary());
            tableDetailFormVO.setAutoInc(tableDetailPO.isAutoInc());
            tableDetailFormVO.setIfUnique(tableDetailPO.isIfUnique());
            tableDetailFormVO.setWithDefaultValue(tableDetailPO.isWithDefaultValue());
            tableDetailFormVO.setNotNull(tableDetailPO.isNotNull());
            tableDetailFormVOList.add(tableDetailFormVO);
        }

        // 3.返回数据
        return tableDetailFormVOList;
    }

    @Override
    public Integer queryRuleCountByCid(int cid) {

        List<TableRulePO> tableRulePOList = tableRuleDao.queryTableRuleByCid(cid);
        return tableRulePOList.size();
    }
}
