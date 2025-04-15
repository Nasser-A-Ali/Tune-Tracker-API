package com.keyin.rest.artist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Artist entities.
 * Provides data access methods for artist-related database operations.
 */
@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    /**
     * Finds an artist by their name.
     *
     * @param name The name to search for
     * @return The artist with the specified name, or null if not found
     */
    Artist findByName(String name);

    /**
     * Gets all artists who debuted in a specific year.
     *
     * @param year The debut year to search for
     * @return List of artists who debuted in the specified year
     */
    List<Artist> findByDebutYear(Integer year);

    /**
     * Searches artists by their genre.
     *
     * @param genre The genre to search for
     * @return List of artists in the specified genre
     */
    List<Artist> findByGenre(String genre);

    /**
     * Gets all artists from a specific country.
     *
     * @param country The country to search for
     * @return List of artists from the specified country
     */
    List<Artist> findByCountry(String country);
}
