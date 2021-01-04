package com.morenanza.excel.rest;

import com.morenanza.excel.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/test/excel")
    public void getExportUsers() throws IOException {
        usersService.generateUsersExport();
    }
}
