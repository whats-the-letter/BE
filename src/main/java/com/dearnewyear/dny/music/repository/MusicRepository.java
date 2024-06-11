package com.dearnewyear.dny.music.repository;

import com.dearnewyear.dny.music.domain.Music;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music, String> {

	Optional<Music> findByYoutubeUrlId(String youtubeUrlId);
}
