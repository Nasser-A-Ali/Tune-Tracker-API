package com.keyin.rest.album;

import com.keyin.rest.artist.Artist;
import com.keyin.rest.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

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

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping("/album")
    public Album createAlbum(@RequestBody Album album) {
        Artist artist = artistService.findById(album.getArtist().getId());

        if (artist == null) {
            throw new RuntimeException("Artist not found with ID: " + album.getArtist().getId());
        }
        album.setArtist(artist);

        return albumService.createAlbum(album);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable long id, @RequestBody Album album) {
        return ResponseEntity.ok(albumService.updateAlbum(id, album));
    }

    @DeleteMapping("album/{id}")
    public void deleteAlbumById(@PathVariable long id) {
        albumService.deleteAlbumById(id);
    }
}
