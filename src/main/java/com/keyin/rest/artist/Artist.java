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
    private int debutYear;
    private String genre;
    private String country;

    @OneToMany // An artist can have many albums
    private List<Album> albums;


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
}
