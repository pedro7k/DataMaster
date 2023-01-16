package com.pedro.domain.form.service.tableDetailForm.impl;

import com.pedro.domain.form.model.res.TableDetailFormRes;
import com.pedro.domain.form.model.res.TableManageFormRes;
import com.pedro.domain.form.model.vo.TableDetailFormVO;
import com.pedro.domain.form.repository.TableDetailFormRepository;
import com.pedro.domain.form.service.tableDetailForm.TableDetailFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableDetailFormServiceImpl implements TableDetailFormService {

    @Resource
    private TableDetailFormRepository tableDetailFormRepository;

    @Override
    public List<TableDetailFormRes> loadTableDetailForm(int tid) {

        // 1.数据拉取
        List<TableDetailFormVO> tableDetailFormVOList = tableDetailFormRepository.loadTableDetailForm(tid);

        // 2.数据转换
        List<TableDetailFormRes> tableDetailFormResList = new ArrayList<>();
        for (TableDetailFormVO tableDetailFormVO : tableDetailFormVOList) {
            TableDetailFormRes oneRes = new TableDetailFormRes();
            oneRes.setCid(tableDetailFormVO.getCid());
            oneRes.setColumnName(tableDetailFormVO.getColumnName());
            oneRes.setDataType(tableDetailFormVO.getDataType());
            oneRes.setPrimary(tableDetailFormVO.isIfPrimary() ? "是" : "否");
            oneRes.setNotNull(tableDetailFormVO.isNotNull() ? "是" : "否");
            oneRes.setWithDefaultValue(tableDetailFormVO.isWithDefaultValue() ? "是" : "否");
            oneRes.setUnique(tableDetailFormVO.isIfUnique() ? "是" : "否");
            oneRes.setAutoInc(tableDetailFormVO.isAutoInc() ? "是" : "否");
            // 检查自定义约束数量
            int ruleCount = tableDetailFormRepository.queryRuleCountByCid(tableDetailFormVO.getCid());
            oneRes.setRuleCount(ruleCount);
            tableDetailFormResList.add(oneRes);
        }

        // 3.返回
        return tableDetailFormResList;
    }
}
