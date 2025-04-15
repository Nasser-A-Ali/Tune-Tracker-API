package com.keyin.rest.album;

import com.keyin.rest.artist.Artist;
import com.keyin.rest.song.Song;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Entity class representing an Album in the music database.
 * An album belongs to one artist and can contain multiple songs.
 */
@Entity
public class Album {
    /** Unique identifier for the album */
    @Id
    @SequenceGenerator(name = "album_sequence", sequenceName = "album_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "album_sequence")
    private long id;

    /** The title of the album */
    private String title;

    /** The year the album was released */
    private int releaseYear;

    /** The musical genre of the album */
    private String genre;

    /** The artist who created the album */
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnoreProperties({ "albums", "songs" })
    private Artist artist;

    /** The list of songs included in the album */
    @ManyToMany
    @JoinTable(name = "album_song", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> listOfSongs;

    /**
     * Gets the unique identifier of the album.
     *
     * @return the album's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the album.
     *
     * @param id the album's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the title of the album.
     *
     * @return the album's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the album.
     *
     * @param title the album's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the artist who created the album.
     *
     * @return the album's artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist who created the album.
     *
     * @param artist the album's artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the release year of the album.
     *
     * @return the album's release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the album.
     *
     * @param releaseYear the album's release year
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the genre of the album.
     *
     * @return the album's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the album.
     *
     * @param genre the album's genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the list of songs in the album.
     *
     * @return the list of songs
     */
    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    /**
     * Sets the list of songs in the album.
     *
     * @param listOfSongs the list of songs
     */
    public void setListOfSongs(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }
}