package com.keyin.rest.song;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.rest.album.Album;
import com.keyin.rest.artist.Artist;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class representing a Song in the music database.
 * A song belongs to one artist and can be included in multiple albums.
 */
@Entity
public class Song {
    /** Unique identifier for the song */
    @Id
    @SequenceGenerator(name = "song_sequence", sequenceName = "song_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "song_sequence")
    private long id;

    /** The title of the song */
    private String title;

    /** The genre of the song */
    private String genre;

    /** The duration of the song in seconds */
    private int duration;

    /** The year the song was released */
    private int releaseYear;

    /** The artist who created the song */
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    /** The list of albums that include this song */
    @ManyToMany(mappedBy = "listOfSongs")
    @JsonIgnore
    private List<Album> albums;

    /**
     * Gets the unique identifier of the song.
     *
     * @return the song's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the song.
     *
     * @param id the song's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the title of the song.
     *
     * @return the song's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title the song's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the artist who created the song.
     *
     * @return the song's artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist who created the song.
     *
     * @param artist the song's artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the genre of the song.
     *
     * @return the song's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre the song's genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the duration of the song in seconds.
     *
     * @return the song's duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param duration the song's duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the release year of the song.
     *
     * @return the song's release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the song.
     *
     * @param releaseYear the song's release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the list of albums that include this song.
     *
     * @return the list of albums
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the list of albums that include this song.
     *
     * @param albums the list of albums
     */
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
