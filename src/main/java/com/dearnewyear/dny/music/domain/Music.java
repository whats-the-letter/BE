package com.dearnewyear.dny.music.domain;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.music.domain.constant.Category;
import com.dearnewyear.dny.music.dto.request.AddMusicRequest;
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

    @Column(name = "music_name", nullable = false)
    private String musicName;

    @Column(name = "music_artist", nullable = false)
    private String musicArtist;

    @Column(name = "youtube_url_id", nullable = false)
    private String youtubeUrlId;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();
}
