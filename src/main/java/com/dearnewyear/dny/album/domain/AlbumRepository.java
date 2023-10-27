package com.dearnewyear.dny.album.domain;

import com.dearnewyear.dny.album.repository.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
