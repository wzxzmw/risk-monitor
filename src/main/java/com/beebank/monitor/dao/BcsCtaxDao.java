package com.beebank.monitor.dao;

import com.beebank.monitor.entity.BcsCtax;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  BcsCtaxDao
 */
@Repository
public interface BcsCtaxDao extends BaseDao<BcsCtax>{

    void deleteAll();

    List<BcsCtax> listCtax(int start, int end);
}
