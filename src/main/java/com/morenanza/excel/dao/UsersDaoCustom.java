package com.morenanza.excel.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDaoCustom {
    List<Object[]> recoverData(String queryCustom);
}
