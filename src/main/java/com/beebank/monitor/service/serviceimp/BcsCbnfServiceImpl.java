package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.BcsCbnfDao;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.entity.CompanyPeopleInfo;
import com.beebank.monitor.service.BcsCbnfService;
import com.beebank.monitor.util.BeanConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.beebank.monitor.entity.BcsCbnf;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  BcsCbnfServiceImpl
 */
@Service("bcsCbnfService")
@Transactional
public class BcsCbnfServiceImpl extends BaseServiceImpl<BcsCbnf> implements BcsCbnfService {

    @Autowired
    private BcsCbnfDao bcsCbnfDao;
    @Autowired
    private CompanyPeopleInfoServiceImpl infoService;

    @Override
    protected BaseDao getBaseDao() {
        return this.bcsCbnfDao;
    }
    private long times = 0L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将数据从cbnf表导入至info表
     * @param count
     * @return
     */
    public void importInfoFromCbnf(int count) {
        //查询表数据量总数
        Long totalCount = bcsCbnfDao.listPageCount(new HashMap());
        times = totalCount%count == 0 ? totalCount/count : totalCount/count+1;
        logger.info("开始将受益人信息导入总表，总计 {} 条数据，{} 次完成！",totalCount,times);
        //分多次查询指定数据，并插入info表
        List<CompanyPeopleInfo> infos = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            importDataOneTime(i,count,infos);
        }
    }

    /**
     * 每次处理固定数量的数
     * @param time 第几次执行
     * @param count 每次执行多少数据
     * @param infos 本次处理info的集合
     */
    public void importDataOneTime(int time,int count, List<CompanyPeopleInfo> infos) {

        List<BcsCbnf> list = bcsCbnfDao.listCbnf(time*count,(time+1)*count);
        for (BcsCbnf cbnf: list) {
            List<CompanyPeopleInfo> infosSub = BeanConvertUtil.cbnfToInfo(cbnf);
            if(infosSub != null && infosSub.size() > 0) {
                infos.addAll(infosSub);
            }

        }
        if(infos != null && infos.size() > 0) {
            infoService.batchInsert(infos);
        }
        logger.info("将受益人信息导入总表，第 {}/{} 次，本次执行共 {} 条",time+1,times,infos.size());
        //插入后清空集合
        infos.clear();

    }

}
