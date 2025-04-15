package com.keyin.rest.song;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Song entities.
 * Extends CrudRepository to provide basic CRUD operations and custom query
 * methods.
 * This interface is automatically implemented by Spring Data JPA.
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    /**
     * Finds all songs with the specified title.
     *
     * @param title The title to search for
     * @return List of songs matching the title
     */
    public List<Song> findByTitle(String title);

    /**
     * Finds all songs of the specified genre.
     *
     * @param genre The genre to search for
     * @return List of songs matching the genre
     */
    public List<Song> findByGenre(String genre);

    /**
     * Finds all songs released in the specified year.
     *
     * @param releaseYear The release year to search for
     * @return List of songs matching the release year
     */
    public List<Song> findByReleaseYear(Integer releaseYear);

    /**
     * Finds all songs by the specified artist.
     *
     * @param artistId The ID of the artist to search for
     * @return List of songs by the specified artist
     */
    public List<Song> findByArtistId(Long artistId);
}
