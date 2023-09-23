package com.dearnewyear.dny.album.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ALBUM")
@Getter
@NoArgsConstructor
public class Album {

    @Id @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
}
