package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.BcsCumiDao;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.entity.CompanyPeopleInfo;
import com.beebank.monitor.service.BcsCumiService;
import com.beebank.monitor.util.BeanConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.beebank.monitor.entity.BcsCumi;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  BcsCumiServiceImpl
 */
@Service("bcsCumiService")
@Transactional
public class BcsCumiServiceImpl extends BaseServiceImpl<BcsCumi> implements BcsCumiService {


    @Autowired
    private BcsCumiDao bcsCumiDao;

    @Override
    protected BaseDao getBaseDao() {
        return this.bcsCumiDao;
    }
    @Autowired
    private CompanyPeopleInfoServiceImpl infoService;
    private long times = 0L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将数据从cumi表导入至info表
     * @param count
     * @return
     */
    public void importInfoFromCumi(int count) {

        //查询表数据量总数
        Long totalCount = bcsCumiDao.listPageCount(new HashMap());
        times = totalCount%count == 0 ? totalCount/count : totalCount/count+1;
        logger.info("开始将控股人信息导入总表，总计 {} 条数据，{} 次完成！",totalCount,times);
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
     */
    public void importDataOneTime(int time,int count, List<CompanyPeopleInfo> infos) {

        List<BcsCumi> list = bcsCumiDao.listCumi(time*count,(time+1)*count);
        for (BcsCumi cumi: list) {
            CompanyPeopleInfo info = BeanConvertUtil.cumiToInfo(cumi);
            if(info != null) {
                infos.add(info);
            }
        }

        if(infos != null && infos.size() > 0) {
            infoService.batchInsert(infos);
        }


        //插入后清空集合
        logger.info("将控股人信息导入总表，第 {}/{} 次，本次执行共 {} 条",time+1,times,infos.size());

        infos.clear();

    }
}
