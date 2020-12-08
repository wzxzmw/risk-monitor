package com.beebank.monitor.dao;

import com.beebank.monitor.entity.BcsCumi;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  BcsCumiDao
 */
@Repository
public interface BcsCumiDao extends BaseDao<BcsCumi>{

    void deleteAll();

    List<BcsCumi> listCumi(int start, int end);
}
