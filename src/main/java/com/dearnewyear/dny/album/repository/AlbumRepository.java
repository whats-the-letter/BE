package com.dearnewyear.dny.album.repository;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByToUser(User user);
}
