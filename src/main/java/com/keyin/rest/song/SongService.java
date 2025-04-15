package com.keyin.rest.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Song entities.
 * Handles business logic and data persistence operations for songs.
 */
@Service
public class SongService {
    /** Repository for song data persistence */
    @Autowired
    private SongRepository songRepository;

    /**
     * Retrieves all songs from the database.
     *
     * @return List of all songs
     */
    public List<Song> getAllSongs() {
        return (List<Song>) songRepository.findAll();
    }

    /**
     * Retrieves a song by its ID.
     *
     * @param id The ID of the song to retrieve
     * @return The song with the specified ID, or null if not found
     */
    public Song getSongById(long id) {
        Optional<Song> songOptional = songRepository.findById(id);
        return songOptional.orElse(null);
    }

    /**
     * Searches for songs by title.
     *
     * @param title The title to search for
     * @return List of songs matching the title
     */
    public List<Song> getSongByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    /**
     * Searches for songs by genre.
     *
     * @param genre The genre to search for
     * @return List of songs matching the genre
     */
    public List<Song> getSongByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    /**
     * Searches for songs by release year.
     *
     * @param releaseYear The release year to search for
     * @return List of songs matching the release year
     */
    public List<Song> getSongByReleaseYear(int releaseYear) {
        return songRepository.findByReleaseYear(releaseYear);
    }

    /**
     * Searches for songs by artist ID.
     *
     * @param artistId The artist ID to search for
     * @return List of songs by the specified artist
     */
    public List<Song> getSongByArtistId(long artistId) {
        return songRepository.findByArtistId(artistId);
    }

    /**
     * Deletes a song by its ID.
     *
     * @param id The ID of the song to delete
     */
    public void deleteSongById(long id) {
        songRepository.deleteById(id);
    }

    /**
     * Creates a new song.
     *
     * @param newSong The song to create
     * @return The created song
     */
    public Song createSong(Song newSong) {
        return songRepository.save(newSong);
    }

    /**
     * Updates an existing song.
     * Only non-null and non-empty fields from the updated song will be applied.
     * For numeric fields (duration, release year), only positive values will be
     * applied.
     *
     * @param id          The ID of the song to update
     * @param updatedSong The updated song data
     * @return The updated song, or null if the song doesn't exist
     */
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
