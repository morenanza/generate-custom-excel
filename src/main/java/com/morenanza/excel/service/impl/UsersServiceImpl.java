package com.morenanza.excel.service.impl;

import com.morenanza.excel.dao.UsersDao;
import com.morenanza.excel.model.SheetData;
import com.morenanza.excel.service.ConfigurationService;
import com.morenanza.excel.service.ExcelService;
import com.morenanza.excel.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ExcelService excelService;

    public void generateUsersExport() throws IOException {
        String usersListData = configurationService.getConfiguration("users-list-data");
        String usersCountData = configurationService.getConfiguration("users-count-data");
        String usersListHeader = configurationService.getConfiguration("users-list-header");
        String usersCountHeader = configurationService.getConfiguration("users-count-header");


        List<Object[]> usersList = usersDao.recoverData(usersListData);
        List<Object[]> usersCount = usersDao.recoverData(usersCountData);
        String[] headerList = usersListHeader.split(",");
        String[] headerCount = usersCountHeader.split(",");
        Map<String, SheetData> sheetDataMap = new HashMap<>();
        sheetDataMap.put("Export", new SheetData(headerList, usersList));
        sheetDataMap.put("Count", new SheetData(headerCount, usersCount));

        excelService.generateExcel(sheetDataMap, "export-user.xlsx");
    }
}
