package com.dearnewyear.dny.music.repository;

import com.dearnewyear.dny.music.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
