package com.keyin.rest.song;

import com.keyin.rest.album.Album;
import jakarta.persistence.ManyToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs(){return (List<Song>) songRepository.findAll();}

    public Song getSongById(long id){
        Optional<Song> songOptional = songRepository.findById(id);

        return songOptional.orElse(null);
    }

    public Song getSongByTitle(String title){return songRepository.findByTitle(title);}
    public Song getSongByArtistId(long artistId){return songRepository.findByArtistId(artistId);}
    public Song getSongByGenre(String genre){return songRepository.findByGenre(genre);}

    public void deleteSongById(long id){songRepository.deleteById(id);}

    public Song createSong(Song newSong){return songRepository.save(newSong);}

    public Song updateSong(long id, Song updatedSong){
        Optional<Song> songToUpdateOptional = songRepository.findById(id);

        if (songToUpdateOptional.isPresent()){
            Song songToUpdate = songToUpdateOptional.get();

            songToUpdate.setTitle(updatedSong.getTitle());
            songToUpdate.setArtistId(updatedSong.getArtistId());
            songToUpdate.setGenre(updatedSong.getGenre());
            songToUpdate.setDuration(updatedSong.getDuration());
            songToUpdate.setReleaseDate(updatedSong.getReleaseDate());
            songToUpdate.setAlbums(updatedSong.getAlbums());

            return songRepository.save(songToUpdate);
        }

        return null;
    }
}
