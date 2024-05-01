package com.dearnewyear.dny.album.repository;

import com.dearnewyear.dny.album.domain.Album;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {

    List<Album> findByToUserId(String toUserId);
}
