package com.balance.util.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by 孔垂云 on 2017/5/14.
 */
@Component
public class BaseDao<T, S> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 获取  NamedParameterJdbcTemplate
     *
     * @return
     */
    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    /**
     * 修改
     *
     * @param sql
     * @param t
     * @return
     * @author 孔垂云
     * @date 2017-05-18
     */
    public int update(String sql, T t) {
        return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(t));
    }

    public int update(String sql, Object[] objects) {
        return jdbcTemplate.update(sql, objects);
    }


    public int delete(String sql, Object[] objects) {
        return jdbcTemplate.update(sql, objects);
    }

    /**
     * 增加多参数模式
     *
     * @param sql
     * @param objects
     * @return
     */
    public T get(String sql, Object[] objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }


    /**
     * 取得当前泛型的实际class名
     *
     * @return
     */
    private Class<T> getClazz() {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public List<T> list(String sql, Object[] objects) {
        List<T> list = jdbcTemplate.query(sql, objects, BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    public List<T> list(String sql, S s) {
        List<T> list = getNamedParameterJdbcTemplate().query(sql, new BeanPropertySqlParameterSource(s), BeanPropertyRowMapper.newInstance(getClazz()));
        return list;
    }

    public int listCount(String sql, S s) {
        return getNamedParameterJdbcTemplate().queryForObject(sql, new BeanPropertySqlParameterSource(s), Integer.class);
    }
}
