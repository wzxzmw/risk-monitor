package com.beebank.monitor.service.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SaveDataService
 *
 * @author : guo
 * @date : 2020-10-13 17:49
 */
@Service
public class SaveDataService {
    @Autowired
    private BcsCbnfServiceImpl cbnfService;
    @Autowired
    private BcsCtaxServiceImpl ctaxService;
    @Autowired
    private BcsCumiServiceImpl cumiService;
    @Autowired
    private BcsCusvd4ServiceImpl cusvd4Service;

    /**
     * 导入数据的方法放在一个事务，避免总表查询到部分数据
     */

    @Transactional
    public void importData(int count) {
        //导入公司法人，负责人信息
        cusvd4Service.importInfoFromCusvd4(count);
        //导入财务负责人信息
        ctaxService.importInfoFromCtax(count);
        //导入控股人信息
        cumiService.importInfoFromCumi(count);
        //导入受益人信息
        cbnfService.importInfoFromCbnf(count);
    }
}
