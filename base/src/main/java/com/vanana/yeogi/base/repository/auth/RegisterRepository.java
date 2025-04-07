package com.vanana.yeogi.base.repository.auth;

import com.vanana.yeogi.base.entity.auth.UserEt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<UserEt, Long> {
}
