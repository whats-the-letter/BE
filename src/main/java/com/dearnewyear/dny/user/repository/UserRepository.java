package com.dearnewyear.dny.user.repository;

import com.dearnewyear.dny.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
