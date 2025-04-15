package com.keyin.rest.artist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.rest.album.Album;
import com.keyin.rest.song.Song;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing an Artist in the music database.
 * An artist can have many albums and songs.
 */
@Entity
public class Artist {
    /** Unique identifier for the artist */
    @Id
    @SequenceGenerator(name = "artist_sequence", sequenceName = "artist_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "artist_sequence")
    private long id;

    /** The name of the artist */
    private String name;

    /** The year the artist debuted */
    private int debutYear;

    /** The primary genre of the artist */
    private String genre;

    /** The country of origin for the artist */
    private String country;

    /** The list of albums created by the artist */
    @OneToMany(mappedBy = "artist", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) // An artist can have many
                                                                                          // albums
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();

    /** The list of songs created by the artist */
    @OneToMany(mappedBy = "artist", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();

    /**
     * Gets the unique identifier of the artist.
     *
     * @return the artist's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the artist.
     *
     * @param id the artist's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name of the artist.
     *
     * @return the artist's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the artist.
     *
     * @param name the artist's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the debut year of the artist.
     *
     * @return the artist's debut year
     */
    public int getDebutYear() {
        return debutYear;
    }

    /**
     * Sets the debut year of the artist.
     *
     * @param debutYear the artist's debut year
     */
    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    /**
     * Gets the genre of the artist.
     *
     * @return the artist's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the artist.
     *
     * @param genre the artist's genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the country of origin for the artist.
     *
     * @return the artist's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of origin for the artist.
     *
     * @param country the artist's country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the list of albums created by the artist.
     *
     * @return the list of albums
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the list of albums created by the artist.
     *
     * @param albums the list of albums
     */
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    /**
     * Gets the list of songs created by the artist.
     *
     * @return the list of songs
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the list of songs created by the artist.
     *
     * @param songs the list of songs
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
