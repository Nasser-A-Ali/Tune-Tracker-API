package com.keyin.rest.artist;


import com.keyin.rest.album.Album;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Artist {
    @Id
    @SequenceGenerator(name = "artist_sequence", sequenceName = "artist_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "artist_sequence")
    private long id;

    private String name;
    private long debutYear;
    private String genre;
    private String country;

    @OneToMany // An artist can have many albums
    private List<Album> albums;




}
