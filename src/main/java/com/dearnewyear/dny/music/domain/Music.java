package com.dearnewyear.dny.music.domain;

import com.dearnewyear.dny.album.domain.Album;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();
}
