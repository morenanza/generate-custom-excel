package com.morenanza.excel.service.impl;

import com.morenanza.excel.dao.ConfigurationDao;
import com.morenanza.excel.model.entity.Configuration;
import com.morenanza.excel.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationDao configurationDao;

    public String getConfiguration(String id){
        Optional<Configuration> configurationOptional = configurationDao.findById(id);
        if(configurationOptional.isEmpty()){
            return null;
        }
        Configuration configuration = configurationOptional.get();
        return configuration.getValue();
    }
}
