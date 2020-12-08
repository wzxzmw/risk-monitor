package com.beebank.monitor.service.serviceimp;




import com.beebank.monitor.dao.BaseDao;
import com.beebank.monitor.service.BaseService;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseDao getBaseDao();

    @Override
    public List<T> listByObj(T t) {
        return getBaseDao().listByObj(t);
    }

    @Override
    public void insert(T t) {
        getBaseDao().insert(t);
    }

    @Override
    public void batchInsert(List<T> list) {
        getBaseDao().batchInsert(list);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    public void batchDeleteByIds(List<String> ids) {
        getBaseDao().batchDeleteByIds(ids);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return (T)getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    public List<T> listPage(Map map) {
        return getBaseDao().listPage(map);
    }

    @Override
    public Long listPageCount(Map map) {
        return getBaseDao().listPageCount(map);
    }
}
