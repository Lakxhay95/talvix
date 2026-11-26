package com.talvix.userservice.repository;

import com.talvix.userservice.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, String> {
}
