package com.keyin.rest.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public List<Song> getAllSongs(){return songService.getAllSongs();}

    @GetMapping("/song_search")
    public Song getSongByTitle(@RequestParam(value = "title", required = false) String title, @RequestParam (value = "artist_id", required = false) Long artistId, @RequestParam (value = "genre", required = false) String genre){

        Song song = new Song();

        if (title != null) {
            song = songService.getSongByTitle(title);
        } else if (artistId != null) {
            song = songService.getSongByArtistId(artistId);
        } else if (genre != null) {
            song = songService.getSongByGenre(genre);
        }

        return song;
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
