package com.keyin.rest.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for managing Artist entities.
 * Provides endpoints for CRUD operations and search functionality.
 */
@RestController
@CrossOrigin
public class ArtistController {
    /** Service for handling artist-related operations */
    @Autowired
    private ArtistService artistService;

    /**
     * Retrieves all artists from the database.
     *
     * @return List of all artists
     */
    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artistService.findAll();
    }

    /**
     * Retrieves a specific artist by their ID.
     *
     * @param id The ID of the artist to retrieve
     * @return The artist with the specified ID
     */
    @GetMapping("/artist/{id}")
    public Artist getArtistById(@PathVariable long id) {
        return artistService.findById(id);
    }

    /**
     * Searches for artists based on various criteria.
     * Only one search parameter will be used at a time, in the following order:
     * debut year, artist ID, and genre.
     *
     * @param artistId  The artist ID to search for (redundant with /artist/{id})
     * @param debutYear The debut year to search for
     * @param genre     The genre to search for
     * @return List of artists matching the search criteria
     */
    @GetMapping("/artist_search")
    public List<Artist> searchArtists(
            @RequestParam(value = "artist_id", required = false) Long artistId,
            @RequestParam(value = "debut_year", required = false) Integer debutYear,
            @RequestParam(value = "genre", required = false) String genre) {

        if (debutYear != null) {
            return artistService.findByDebutYear(debutYear);
        } else if (artistId != null) {
            return List.of(artistService.findById(artistId));
        } else if (genre != null) {
            return artistService.findByGenre(genre);
        }

        return new ArrayList<>();
    }

    /**
     * Creates a new artist.
     *
     * @param artist The artist to create
     * @return The created artist
     */
    @PostMapping("/artist")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    /**
     * Updates an existing artist.
     *
     * @param id     The ID of the artist to update
     * @param artist The updated artist data
     * @return The updated artist
     */
    @PutMapping("/artist/{id}")
    public Artist updateArtist(@PathVariable long id,
            @RequestBody Artist artist) {
        return artistService.updateArtist(id, artist);
    }

    /**
     * Deletes an artist by their ID.
     *
     * @param id The ID of the artist to delete
     */
    @DeleteMapping("/artist/{id}")
    public void deleteArtistById(@PathVariable long id) {
        artistService.deleteArtistById(id);
    }
}
