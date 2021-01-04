package com.morenanza.excel.dao;

import com.morenanza.excel.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersDao extends JpaRepository<Users, UUID>, UsersDaoCustom {
}
