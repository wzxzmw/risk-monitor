package com.beebank.monitor.util;

import com.beebank.monitor.entity.*;
import com.beebank.monitor.monitorconst.StaticConstant;
import com.beebank.monitor.monitorenum.BossIndEnum;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanConvertUtil
 *
 * @description : 实体类转换的工具类
 * @author : guo
 * @date : 2020-09-23 13:20
 */
public class BeanConvertUtil {

    /**
     * 将法人、财务负责人对象转为info实体对象
     * @param cusvd4
     * @return
     */
    public static CompanyPeopleInfo cusvd4ToInfo(BcsCusvd4 cusvd4) {
        if(cusvd4 != null
                && !StringUtils.isEmpty( cusvd4.getBossInd())
                && !StringUtils.isEmpty( cusvd4.getCustNo())) {
            CompanyPeopleInfo info = new CompanyPeopleInfo();
            info.setCustNo(cusvd4.getCustNo());
            info.setIds(cusvd4.getBossIdNo());
            info.setIdType(cusvd4.getBossIdType());
            info.setDesc(BossIndEnum.getValueByCode(cusvd4.getBossInd()));
            info.setName(cusvd4.getBossName());
            return info;
        }

        return null;
    }

    /**
     * 将控股人实体转为info实体对象
     * @param cumi
     * @return
     */
    public static CompanyPeopleInfo cumiToInfo(BcsCumi cumi) {
        if(cumi != null && !StringUtils.isEmpty(cumi.getSharePerIdnum())
                && !StringUtils.isEmpty( cumi.getCustNo())) {
            CompanyPeopleInfo info = new CompanyPeopleInfo();
            info.setIds(cumi.getSharePerIdnum());
            info.setIdType(cumi.getSharePerIdty());
            info.setName(cumi.getShareHoldPerson());
            info.setDesc(StaticConstant.CUMI);
            info.setCustNo(cumi.getCustNo());
            return info;

        }
        return null;
    }

    /**
     * 将财务负责人实体转为info实体对象
     * @param ctax
     * @return
     */
    public static CompanyPeopleInfo ctaxToInfo(BcsCtax ctax) {
        if(ctax != null && !StringUtils.isEmpty(ctax.getFinMasIdNo())
                && !StringUtils.isEmpty( ctax.getCustNo())) {
            CompanyPeopleInfo info = new CompanyPeopleInfo();
            info.setIds(ctax.getFinMasIdNo());
            info.setIdType(ctax.getFinMasIdTy());
            info.setName(ctax.getFinMaster());
            info.setDesc(StaticConstant.CTAX);
            info.setCustNo(ctax.getCustNo());
            return info;
        }
        return null;
    }

    /**
     * 将受益人实体转为info实体对象
     * @param cbnf
     * @return
     */
    public static List<CompanyPeopleInfo> cbnfToInfo(BcsCbnf cbnf) {
        BeanMap beanMap = BeanMap.create(cbnf);
        if(beanMap != null && !StringUtils.isEmpty( cbnf.getCustNo())) {
            List<CompanyPeopleInfo> list = new ArrayList<>(4);
            for (int i = 1; i <= 4; i++) {
                if(!StringUtils.isEmpty(beanMap.get("bnfIdNo"+i))) {
                    if(StringUtils.isEmpty(beanMap.get("bnfIdNo"+i))) {
                        continue;
                    }
                    CompanyPeopleInfo info = new CompanyPeopleInfo();
                    info.setCustNo(cbnf.getCustNo());
                    info.setDesc(StaticConstant.CBNF);
                    info.setIds(beanMap.get("bnfIdNo"+i).toString());
                    if(!StringUtils.isEmpty(beanMap.get("bnfName"+i))) {
                        info.setName(beanMap.get("bnfName"+i).toString());
                    }

                    if(!StringUtils.isEmpty(beanMap.get("bnfIdType"+i))) {
                        info.setIdType(beanMap.get("bnfIdType"+i).toString());
                    }

                    if(!StringUtils.isEmpty(beanMap.get("bnfAddr"+i))) {
                        info.setAddress(beanMap.get("bnfAddr"+i).toString());
                    }
                    list.add(info);
                }

            }
            return list;
        }
        return null;
    }
}
