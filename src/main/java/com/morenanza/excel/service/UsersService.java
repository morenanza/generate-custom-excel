package com.morenanza.excel.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UsersService {
    void generateUsersExport() throws IOException;
}
