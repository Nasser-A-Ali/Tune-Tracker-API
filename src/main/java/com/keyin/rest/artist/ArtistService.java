package com.keyin.rest.artist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;


    // Create
    public Artist createArtist(Artist newArtist) {
        return artistRepository.save(newArtist);
    }


    // Find/Read
    public Artist findById(long id) {
        Optional<Artist> divisionOptional = artistRepository.findById(id);
        return divisionOptional.orElse(null);
    }

    public Artist findByName(String name) {
        return artistRepository.findByName(name);
    }

    public List<Artist> findAll(){
        return (List<Artist>) artistRepository.findAll();
    }

    public List<Artist> findByDebutYear(int year) {
        return artistRepository.findByDebutYear(year);
    }

    public List<Artist> findByGenre(String genre) {
        return artistRepository.findByGenre(genre);
    }

    public List<Artist> findByCountry(String country) {
        return artistRepository.findByCountry(country);
    }


    // Update
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
    
    // Delete
    public void deleteArtistById(long id) {
        artistRepository.deleteById(id);
    }

    public void deleteArtistByName(String name) {
        artistRepository.delete(findByName(name));
    }


}
