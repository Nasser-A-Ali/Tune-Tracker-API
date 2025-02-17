package com.keyin.rest.artist;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.rest.album.Album;
import com.keyin.rest.song.Song;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {
    @Id
    @SequenceGenerator(name = "artist_sequence", sequenceName = "artist_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "artist_sequence")
    private long id;

    private String name;
    private int debutYear;
    private String genre;
    private String country;

    @OneToMany(mappedBy = "artist", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // An artist can have many albums
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
