package com.dearnewyear.dny.music.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MUSIC")
@Getter
@NoArgsConstructor
public class Music {

    @Id @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;
}
