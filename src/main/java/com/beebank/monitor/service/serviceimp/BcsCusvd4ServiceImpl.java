package com.beebank.monitor.service.serviceimp;
import com.beebank.monitor.dao.BcsCusvd4Dao;
import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.entity.CompanyPeopleInfo;
import com.beebank.monitor.service.BcsCusvd4Service;
import com.beebank.monitor.util.BeanConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.beebank.monitor.entity.BcsCusvd4;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  BcsCusvd4ServiceImpl
 */
@Service("bcsCusvd4Service")
@Transactional
public class BcsCusvd4ServiceImpl extends BaseServiceImpl<BcsCusvd4> implements BcsCusvd4Service {


    @Autowired
    private BcsCusvd4Dao bcsCusvd4Dao;
    @Autowired
    private CompanyPeopleInfoServiceImpl infoService;

    @Override
    protected BaseDao getBaseDao() {
        return this.bcsCusvd4Dao;
    }

    private long times = 0L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将数据从cusvd4表导入至info表
     * @param count
     * @return
     */
    public void importInfoFromCusvd4(int count) {
        //查询表数据量总数
        Long totalCount = bcsCusvd4Dao.listPageCount(new HashMap());
        times = totalCount%count == 0 ? totalCount/count : totalCount/count+1;
        logger.info("将企业法人及负责人信息导入总表，总计 {} 条数据，{} 次完成！",totalCount,times);
        List<CompanyPeopleInfo> infos = new ArrayList<>();
        //分多次查询指定数据，并插入info表
        for (int i = 0; i < times; i++) {
            importDataOneTime(i,count,infos);
        }
    }

    /**
     * 每次处理固定数量的数
     * @param time 第几次执行
     * @param count 每次执行多少数据
     * @param count 每次执行多少数据
     * @param infos 每次的集合
     */
    public void importDataOneTime(int time,int count,List<CompanyPeopleInfo> infos ) {

        List<BcsCusvd4> list = bcsCusvd4Dao.listCusvd4(time*count,(time+1)*count);

        for (BcsCusvd4 cusvd: list) {
            CompanyPeopleInfo info = BeanConvertUtil.cusvd4ToInfo(cusvd);
            if(info !=null ) {
                infos.add(info);
            }
        }
        if(infos != null && infos.size() > 0) {
            infoService.batchInsert(infos);
        }

        logger.info("将入企业法人及负责人信息导入总表，第 {}/{} 次，本次执行共 {} 条",time+1,times,infos.size());
        infos.clear();

    }
}
