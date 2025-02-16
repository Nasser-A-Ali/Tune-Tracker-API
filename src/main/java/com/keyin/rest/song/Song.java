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
    private long artistId;
    private String genre;
    private int duration;
    private String releaseDate;

    @ManyToMany // A song can exist in many albums (ex. singles & full albums)
    // & an album can have many sounds
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

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
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
