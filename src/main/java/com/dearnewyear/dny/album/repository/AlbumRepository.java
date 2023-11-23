package com.dearnewyear.dny.album.repository;

import com.dearnewyear.dny.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
