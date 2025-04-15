package com.keyin.rest.album;

import com.keyin.rest.artist.Artist;
import com.keyin.rest.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for managing Album entities.
 * Provides endpoints for CRUD operations and search functionality.
 */
@RestController
@CrossOrigin
public class AlbumController {
    /** Service for handling album-related operations */
    @Autowired
    private AlbumService albumService;

    /** Service for handling artist-related operations */
    @Autowired
    private ArtistService artistService;

    /**
     * Retrieves all albums from the database.
     *
     * @return List of all albums
     */
    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    /**
     * Searches for albums based on various criteria.
     * Only one search parameter will be used at a time, in the following order:
     * title, release year, artist ID, and genre.
     *
     * @param title       The album title to search for
     * @param releaseYear The release year to search for
     * @param genre       The genre to search for
     * @param artistId    The artist ID to search for
     * @return List of albums matching the search criteria
     */
    @GetMapping("/album_search")
    public List<Album> getAlbumByTitle(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "release_year", required = false) Integer releaseYear,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "artist_id", required = false) Long artistId) {

        List<Album> listOfAlbums = new ArrayList<>();

        if (title != null) {
            listOfAlbums = albumService.getAlbumByTitle(title);
        } else if (releaseYear != null) {
            listOfAlbums = albumService.getAlbumByReleaseYear(releaseYear);
        } else if (artistId != null) {
            listOfAlbums = albumService.getAlbumByArtistId(artistId);
        } else if (genre != null) {
            listOfAlbums = albumService.getAlbumByGenre(genre);
        }

        return listOfAlbums;
    }

    /**
     * Retrieves a specific album by its ID.
     *
     * @param id The ID of the album to retrieve
     * @return The album with the specified ID
     */
    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable long id) {
        return albumService.getAlbumById(id);
    }

    /**
     * Creates a new album.
     * Verifies that the associated artist exists before creating the album.
     *
     * @param album The album to create
     * @return The created album
     * @throws RuntimeException if the associated artist is not found
     */
    @PostMapping("/album")
    public Album createAlbum(@RequestBody Album album) {
        Artist artist = artistService.findById(album.getArtist().getId());

        if (artist == null) {
            throw new RuntimeException("Artist not found with ID: " + album.getArtist().getId());
        }

        album.setArtist(artist);

        return albumService.createAlbum(album);
    }

    /**
     * Updates an existing album.
     *
     * @param id    The ID of the album to update
     * @param album The updated album data
     * @return The updated album wrapped in a ResponseEntity
     */
    @PutMapping("/album/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable long id, @RequestBody Album album) {
        return ResponseEntity.ok(albumService.updateAlbum(id, album));
    }

    /**
     * Deletes an album by its ID.
     *
     * @param id The ID of the album to delete
     */
    @DeleteMapping("album/{id}")
    public void deleteAlbumById(@PathVariable long id) {
        albumService.deleteAlbumById(id);
    }
}
