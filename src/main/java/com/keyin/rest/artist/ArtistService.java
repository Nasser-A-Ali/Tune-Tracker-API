package com.keyin.rest.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Artist entities.
 * Handles business logic and data persistence operations for artists.
 */
@Service
public class ArtistService {
    /** Repository for artist data persistence */
    @Autowired
    private ArtistRepository artistRepository;

    /**
     * Creates a new artist.
     *
     * @param newArtist The artist to create
     * @return The created artist
     */
    public Artist createArtist(Artist newArtist) {
        return artistRepository.save(newArtist);
    }

    /**
     * Retrieves an artist by their ID.
     *
     * @param id The ID of the artist to retrieve
     * @return The artist with the specified ID, or null if not found
     */
    public Artist findById(long id) {
        Optional<Artist> divisionOptional = artistRepository.findById(id);
        return divisionOptional.orElse(null);
    }

    /**
     * Retrieves an artist by their name.
     *
     * @param name The name of the artist to retrieve
     * @return The artist with the specified name, or null if not found
     */
    public Artist findByName(String name) {
        return artistRepository.findByName(name);
    }

    /**
     * Retrieves all artists from the database.
     *
     * @return List of all artists
     */
    public List<Artist> findAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    /**
     * Searches for artists by debut year.
     *
     * @param year The debut year to search for
     * @return List of artists who debuted in the specified year
     */
    public List<Artist> findByDebutYear(int year) {
        return artistRepository.findByDebutYear(year);
    }

    /**
     * Searches for artists by genre.
     *
     * @param genre The genre to search for
     * @return List of artists in the specified genre
     */
    public List<Artist> findByGenre(String genre) {
        return artistRepository.findByGenre(genre);
    }

    /**
     * Searches for artists by country.
     *
     * @param country The country to search for
     * @return List of artists from the specified country
     */
    public List<Artist> findByCountry(String country) {
        return artistRepository.findByCountry(country);
    }

    /**
     * Updates an existing artist.
     * Only updates fields that are provided in the updatedArtist parameter.
     * Maintains existing values for fields that are not provided.
     *
     * @param id The ID of the artist to update
     * @param updatedArtist The updated artist data
     * @return The updated artist, or null if not found
     */
    public Artist updateArtist(long id, Artist updatedArtist) {
        Optional<Artist> artistToUpdateOptional = artistRepository.findById(id);
    
        if (artistToUpdateOptional.isPresent()) {
            Artist artistToUpdate = artistToUpdateOptional.get();
    
            artistToUpdate.setName(
                    updatedArtist.getName() != null && !updatedArtist.getName().isBlank()
                            ? updatedArtist.getName()
                            : artistToUpdate.getName());
    
            artistToUpdate.setDebutYear(
                    updatedArtist.getDebutYear() > 0
                            ? updatedArtist.getDebutYear()
                            : artistToUpdate.getDebutYear());
    
            artistToUpdate.setGenre(
                    updatedArtist.getGenre() != null && !updatedArtist.getGenre().isBlank()
                            ? updatedArtist.getGenre()
                            : artistToUpdate.getGenre());
    
            artistToUpdate.setCountry(
                    updatedArtist.getCountry() != null && !updatedArtist.getCountry().isBlank()
                            ? updatedArtist.getCountry()
                            : artistToUpdate.getCountry());
    
            return artistRepository.save(artistToUpdate);
        }
    
        return null;
    }
    
    /**
     * Deletes an artist by their ID.
     *
     * @param id The ID of the artist to delete
     */
    public void deleteArtistById(long id) {
        artistRepository.deleteById(id);
    }

    /**
     * Deletes an artist by their name.
     *
     * @param name The name of the artist to delete
     */
    public void deleteArtistByName(String name) {
        artistRepository.delete(findByName(name));
    }
}
