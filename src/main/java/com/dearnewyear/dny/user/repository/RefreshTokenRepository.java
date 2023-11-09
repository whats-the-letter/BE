package com.dearnewyear.dny.user.repository;

import com.dearnewyear.dny.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
