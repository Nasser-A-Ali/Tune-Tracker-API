package com.keyin.rest.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        return (List<Album>) albumRepository.findAll();
    }

    public Album getAlbumById(long id) {
        Optional<Album> albumOptional = albumRepository.findById(id);
        return albumOptional.orElse(null);
    }

    public List<Album> getAlbumByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    public List<Album> getAlbumByArtistId(long artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    public List<Album> getAlbumByReleaseYear(int releaseYear) {
        return albumRepository.findByReleaseYear(releaseYear);
    }

    public List<Album> getAlbumByGenre(String genre) {
        return albumRepository.findByGenre(genre);
    }

    public void deleteAlbumById(long id) {
        albumRepository.deleteById(id);
    }

    public Album createAlbum(Album newAlbum) {
        return albumRepository.save(newAlbum);
    }

    public Album updateAlbum(long id, Album updatedAlbum) {
        Optional<Album> albumToUpdateOptional = albumRepository.findById(id);

        if (albumToUpdateOptional.isPresent()) {
            Album albumToUpdate = albumToUpdateOptional.get();

            // Keep existing fields if not provided in the request
            albumToUpdate
                    .setTitle(updatedAlbum.getTitle() != null ? updatedAlbum.getTitle() : albumToUpdate.getTitle());
            albumToUpdate
                    .setArtist(updatedAlbum.getArtist() != null ? updatedAlbum.getArtist() : albumToUpdate.getArtist());
                    
            albumToUpdate.setReleaseYear(updatedAlbum.getReleaseYear() <= 0 ? updatedAlbum.getReleaseYear()
                    : albumToUpdate.getReleaseYear());
            albumToUpdate
                    .setGenre(updatedAlbum.getGenre() != null ? updatedAlbum.getGenre() : albumToUpdate.getGenre());

            if (updatedAlbum.getListOfSongs() != null) {
                albumToUpdate.setListOfSongs(updatedAlbum.getListOfSongs());
            }
            return albumRepository.save(albumToUpdate);
        }

        return null;
    }
}
