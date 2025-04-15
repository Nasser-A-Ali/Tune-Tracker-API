package com.keyin.rest.album;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Album entities.
 * Provides data access methods for album-related database operations.
 */
@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    /**
     * Finds albums by matching the title exactly.
     *
     * @param title The title to search for
     * @return List of albums with the specified title
     */
    public List<Album> findByTitle(String title);

    /**
     * Gets all albums from a specific year.
     *
     * @param releaseYear The release year to search for
     * @return List of albums released in the specified year
     */
    public List<Album> findByReleaseYear(int releaseYear);

    /**
     * Searches albums by their genre.
     *
     * @param genre The genre to search for
     * @return List of albums in the specified genre
     */
    public List<Album> findByGenre(String genre);

    /**
     * Gets all albums by a specific artist.
     *
     * @param artistId The artist ID to search for
     * @return List of albums by the specified artist
     */
    public List<Album> findByArtistId(long artistId);
}