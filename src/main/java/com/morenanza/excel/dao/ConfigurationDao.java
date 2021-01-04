package com.morenanza.excel.dao;

import com.morenanza.excel.model.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationDao extends JpaRepository<Configuration, String> {
}
