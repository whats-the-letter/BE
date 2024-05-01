package com.dearnewyear.dny.user.repository;

import com.dearnewyear.dny.user.domain.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
