package com.keyin.rest.album;

import com.keyin.rest.artist.Artist;
import com.keyin.rest.song.Song;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Album {
    @Id
    @SequenceGenerator(name = "album_sequence", sequenceName = "album_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "album_sequence")
    private long id;

    private String title;
    @ManyToOne // An artist may have many albums, but an album only has 1 artist
    private Artist artist;
    private int releaseYear;
    private int numberOfSongs;
    private String genre;


    @ManyToMany // Songs can appear in multiple albums
             //  & Albums can contain multiple songs
    // **Opt to add @JoinTable for more constraint control**
    private List<Song> songs;

//    @OneToMany
//    private Song song;
}