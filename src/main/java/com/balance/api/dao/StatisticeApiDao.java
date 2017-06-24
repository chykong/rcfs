package com.balance.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 */
@Repository
public class StatisticeApiDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


}
