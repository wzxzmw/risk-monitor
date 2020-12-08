package com.beebank.monitor.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BaseDao<T> {
    /**
     * 根据条件查询list
     * @param t
     * @return
     */
    List<T> listByObj(T t);

    /**
     * 单条插入
     *
     * @param t
     */
    void insert(T t);

    /**
     * 批量插入
     * @param t
     */
    void batchInsert(List<T> t);


    /**
     * 单条记录更新
     * @param t
     * @return
     */
    int updateByPrimaryKey(T t);


    /**
     * 根据id删除
     * @param id
     */
    void deleteByPrimaryKey(String id);

    /**
     * 根据id集合批量删除
     * @param ids
     */
    void batchDeleteByIds(List<String> ids);

    /**
     * 根据主键查询单条记录
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);

    /**
     * 根据条件查询list(分页)
     * @param map
     * @return
     */
    List<T> listPage(Map map);

    /**
     * 根据条件查询总条数
     * @param map
     * @return
     */
    Long listPageCount (Map map);
}
