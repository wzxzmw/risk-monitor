package com.beebank.monitor.dao;

import com.beebank.monitor.entity.BcsCbnf;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  BcsCbnfDao
 */
@Repository
public interface BcsCbnfDao extends BaseDao<BcsCbnf>{

    void deleteAll();

    List<BcsCbnf> listCbnf(int start, int end);
}
