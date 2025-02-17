package com.keyin.rest.song;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.rest.album.Album;
import com.keyin.rest.artist.Artist;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Song {
    @Id
    @SequenceGenerator(name = "song_sequence", sequenceName = "song_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "song_sequence")
    private long id;

    private String title;
    private String genre;
    private int duration;
    private String releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;


    // A song can exist in many albums (ex. singles & full albums)
    // & an album can have many sounds
    @ManyToMany(mappedBy = "listOfSongs")
    @JsonIgnore
    private List<Album> albums;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
