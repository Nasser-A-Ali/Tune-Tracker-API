package com.keyin.rest.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public List<Song> getAllSongs(){return songService.getAllSongs();}

    @GetMapping("/song_search")
    public List<Song> getSongByTitle(@RequestParam(value = "title", required = false) String title, @RequestParam (value = "genre", required = false) String genre, @RequestParam (value = "release_year", required = false) Integer releaseYear, @RequestParam (value = "artist_id", required = false) Long artistId){

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

    @GetMapping("/song/{id}")
    public Song getSongById(@PathVariable long id){return songService.getSongById(id);}

    @PostMapping("/song")
    public Song createSong(@RequestBody Song song){return songService.createSong(song);}

    @PutMapping("/song/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable long id ,@RequestBody Song song){
        return ResponseEntity.ok(songService.updateSong(id, song));
    }

    @DeleteMapping("song/{id}")
    public void deleteSongById(@PathVariable long id){songService.deleteSongById(id);}
}
