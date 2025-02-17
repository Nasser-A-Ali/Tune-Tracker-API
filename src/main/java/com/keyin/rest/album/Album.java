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
    private int releaseYear;
    private int numberOfSongs;
    private String genre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToMany
    @JoinTable(
            name = "album_song",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> listOfSongs;

    // Getters and Setters
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public void setListOfSongs(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }
}