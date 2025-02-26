package com.keyin.rest.artist;


import com.keyin.rest.song.Song;
import com.keyin.rest.song.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongRepository songRepository;


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

    public List<Artist> findBySongTitle(String title) {
        List<Song> songs = songRepository.findByTitle(title);

        List<Artist> artists = new ArrayList<>();
        for (Song song : songs) {
            artists.add(song.getArtist());
        }
        return artists;
    }


    public List<Artist> findAll() {
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
            artistToUpdate.setName(updatedArtist.getName());
            artistToUpdate.setDebutYear(updatedArtist.getDebutYear());
            artistToUpdate.setGenre(updatedArtist.getGenre());
            artistToUpdate.setCountry(updatedArtist.getCountry());
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
