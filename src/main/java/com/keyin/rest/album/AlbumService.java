package com.keyin.rest.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Album entities.
 * Handles business logic and data persistence operations for albums.
 */
@Service
public class AlbumService {

    /** Repository for album data persistence */
    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Retrieves all albums from the database.
     *
     * @return List of all albums
     */
    public List<Album> getAllAlbums() {
        return (List<Album>) albumRepository.findAll();
    }

    /**
     * Retrieves an album by its ID.
     *
     * @param id The ID of the album to retrieve
     * @return The album with the specified ID, or null if not found
     */
    public Album getAlbumById(long id) {
        Optional<Album> albumOptional = albumRepository.findById(id);
        return albumOptional.orElse(null);
    }

    /**
     * Searches for albums by title.
     *
     * @param title The title to search for
     * @return List of albums with the specified title
     */
    public List<Album> getAlbumByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    /**
     * Searches for albums by artist ID.
     *
     * @param artistId The artist ID to search for
     * @return List of albums by the specified artist
     */
    public List<Album> getAlbumByArtistId(long artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    /**
     * Searches for albums by release year.
     *
     * @param releaseYear The release year to search for
     * @return List of albums released in the specified year
     */
    public List<Album> getAlbumByReleaseYear(int releaseYear) {
        return albumRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Searches for albums by genre.
     *
     * @param genre The genre to search for
     * @return List of albums in the specified genre
     */
    public List<Album> getAlbumByGenre(String genre) {
        return albumRepository.findByGenre(genre);
    }

    /**
     * Deletes an album by its ID.
     *
     * @param id The ID of the album to delete
     */
    public void deleteAlbumById(long id) {
        albumRepository.deleteById(id);
    }

    /**
     * Creates a new album.
     *
     * @param newAlbum The album to create
     * @return The created album
     */
    public Album createAlbum(Album newAlbum) {
        return albumRepository.save(newAlbum);
    }

    /**
     * Updates an existing album.
     * Only updates fields that are provided in the updatedAlbum parameter.
     * Maintains existing values for fields that are not provided.
     *
     * @param id           The ID of the album to update
     * @param updatedAlbum The updated album data
     * @return The updated album, or null if not found
     */
    public Album updateAlbum(long id, Album updatedAlbum) {
        Optional<Album> albumToUpdateOptional = albumRepository.findById(id);

        if (albumToUpdateOptional.isPresent()) {
            Album albumToUpdate = albumToUpdateOptional.get();

            // Keep existing fields if not provided in the request
            albumToUpdate
                    .setTitle(updatedAlbum.getTitle() != null ? updatedAlbum.getTitle() : albumToUpdate.getTitle());
            albumToUpdate
                    .setArtist(updatedAlbum.getArtist() != null ? updatedAlbum.getArtist() : albumToUpdate.getArtist());

            albumToUpdate.setReleaseYear(updatedAlbum.getReleaseYear() >= 0 ? updatedAlbum.getReleaseYear()
                    : albumToUpdate.getReleaseYear());

            albumToUpdate
                    .setGenre(updatedAlbum.getGenre() != null ? updatedAlbum.getGenre() : albumToUpdate.getGenre());

            if (updatedAlbum.getListOfSongs() != null) {
                albumToUpdate.setListOfSongs(updatedAlbum.getListOfSongs());
            }
            return albumRepository.save(albumToUpdate);
        }

        return null;
    }
}
