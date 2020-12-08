package com.beebank.monitor.dao;

import com.beebank.monitor.entity.BcsCusvd4;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  BcsCusvd4Dao
 */
@Repository
public interface BcsCusvd4Dao extends BaseDao<BcsCusvd4>{

    void deleteAll();

    List<BcsCusvd4> listCusvd4(int start,int end);
}
