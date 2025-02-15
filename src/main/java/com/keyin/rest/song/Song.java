package com.keyin.rest.song;

import com.keyin.rest.album.Album;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Song {
    @Id
    @SequenceGenerator(name = "song_sequence", sequenceName = "song_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "song_sequence")
    private long id;

    private String title;
    private int artistId;
    private String genre;
    private int duration;
    private String releaseDate;

    @ManyToMany // A song can exist in many albums (ex. singles & full albums)
    // & an album can have many sounds
    private List<Album> albums;

}
