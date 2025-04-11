package com.keyin.rest.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs() {
        return (List<Song>) songRepository.findAll();
    }

    public Song getSongById(long id) {
        Optional<Song> songOptional = songRepository.findById(id);

        return songOptional.orElse(null);
    }

    public List<Song> getSongByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public List<Song> getSongByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    public List<Song> getSongByReleaseYear(int releaseYear) {
        return songRepository.findByReleaseYear(releaseYear);
    }

    public List<Song> getSongByArtistId(long artistId) {
        return songRepository.findByArtistId(artistId);
    }

    public void deleteSongById(long id) {
        songRepository.deleteById(id);
    }

    public Song createSong(Song newSong) {
        return songRepository.save(newSong);
    }

    public Song updateSong(long id, Song updatedSong) {
        Optional<Song> songToUpdateOptional = songRepository.findById(id);

        if (songToUpdateOptional.isPresent()) {
            Song songToUpdate = songToUpdateOptional.get();

            songToUpdate.setTitle(
                    updatedSong.getTitle() != null && !updatedSong.getTitle().isBlank()
                            ? updatedSong.getTitle()
                            : songToUpdate.getTitle());

            songToUpdate.setArtist(
                    updatedSong.getArtist() != null
                            ? updatedSong.getArtist()
                            : songToUpdate.getArtist());

            songToUpdate.setGenre(
                    updatedSong.getGenre() != null && !updatedSong.getGenre().isBlank()
                            ? updatedSong.getGenre()
                            : songToUpdate.getGenre());

            songToUpdate.setDuration(
                    updatedSong.getDuration() > 0
                            ? updatedSong.getDuration()
                            : songToUpdate.getDuration());

            songToUpdate.setReleaseYear(
                    updatedSong.getReleaseYear() > 0
                            ? updatedSong.getReleaseYear()
                            : songToUpdate.getReleaseYear());

            if (updatedSong.getAlbums() != null) {
                songToUpdate.setAlbums(updatedSong.getAlbums());
            }

            return songRepository.save(songToUpdate);
        }

        return null;
    }

}
