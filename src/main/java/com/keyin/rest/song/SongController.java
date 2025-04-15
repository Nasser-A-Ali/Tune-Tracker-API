package com.keyin.rest.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for managing Song entities.
 * Provides endpoints for CRUD operations and search functionality.
 */
@RestController
@CrossOrigin
public class SongController {
    /** Service for handling song-related operations */
    @Autowired
    private SongService songService;

    /**
     * Retrieves all songs from the database.
     *
     * @return List of all songs
     */
    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    /**
     * Searches for songs based on various criteria.
     * Only one search parameter will be used at a time, in the following order:
     * title, genre, release year, and artist ID.
     *
     * @param title       The song title to search for
     * @param genre       The genre to search for
     * @param releaseYear The release year to search for
     * @param artistId    The artist ID to search for
     * @return List of songs matching the search criteria
     */
    @GetMapping("/song_search")
    public List<Song> getSongByTitle(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "release_year", required = false) Integer releaseYear,
            @RequestParam(value = "artist_id", required = false) Long artistId) {

        List<Song> listOfSongs = new ArrayList<>();

        if (title != null) {
            listOfSongs = songService.getSongByTitle(title);
        } else if (genre != null) {
            listOfSongs = songService.getSongByGenre(genre);
        } else if (releaseYear != null) {
            listOfSongs = songService.getSongByReleaseYear(releaseYear);
        } else if (artistId != null) {
            listOfSongs = songService.getSongByArtistId(artistId);
        }

        return listOfSongs;
    }

    /**
     * Retrieves a specific song by its ID.
     *
     * @param id The ID of the song to retrieve
     * @return The song with the specified ID
     */
    @GetMapping("/song/{id}")
    public Song getSongById(@PathVariable long id) {
        return songService.getSongById(id);
    }

    /**
     * Creates a new song.
     *
     * @param song The song to create
     * @return The created song
     */
    @PostMapping("/song")
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    /**
     * Updates an existing song.
     *
     * @param id   The ID of the song to update
     * @param song The updated song data
     * @return The updated song wrapped in a ResponseEntity
     */
    @PutMapping("/song/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable long id, @RequestBody Song song) {
        return ResponseEntity.ok(songService.updateSong(id, song));
    }

    /**
     * Deletes a song by its ID.
     *
     * @param id The ID of the song to delete
     */
    @DeleteMapping("song/{id}")
    public void deleteSongById(@PathVariable long id) {
        songService.deleteSongById(id);
    }
}
