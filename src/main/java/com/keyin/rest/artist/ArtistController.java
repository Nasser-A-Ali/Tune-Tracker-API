package com.keyin.rest.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin

public class ArtistController {
    @Autowired
    private ArtistService artistService;


    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artistService.findAll();
    }

    @GetMapping("/artist/{id}")
    public Artist getArtistById(@PathVariable long id) {
        return artistService.findById(id);
    }

    @GetMapping("/artist_search")
    public List<Artist> searchArtists(
            @RequestParam(value = "artist_id", required = false) Long artistId, // unncesesary (/artist/{id}) does same thing
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


    @PostMapping("/artist")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    @PutMapping("/artist/{id}")
    public Artist updateArtist(@PathVariable long id,
                               @RequestBody Artist artist) {
        return artistService.updateArtist(id, artist);
    }

    @DeleteMapping("/artist/{id}")
    public void deleteArtistById(@PathVariable long id) {
        artistService.deleteArtistById(id);
    }

}
