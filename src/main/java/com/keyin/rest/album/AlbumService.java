package com.keyin.rest.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository AlbumRepository;

    public List<Album> getAllAlbums(){return (List<Album>) AlbumRepository.findAll();}

    public Album getAlbumById(long id){
        Optional<Album> AlbumOptional = AlbumRepository.findById(id);

        return AlbumOptional.orElse(null);
    }

    public List<Album> getAlbumByTitle(String title){return AlbumRepository.findByTitle(title);}
    public List<Album> getAlbumByArtistId(long artistId){return AlbumRepository.findByArtistId(artistId);}
    public List<Album> getAlbumByReleaseYear(int releaseYear){return AlbumRepository.findByReleaseYear(releaseYear);}
    public List<Album> getAlbumByGenre(String genre){return AlbumRepository.findByGenre(genre);}

    public void deleteAlbumById(long id){AlbumRepository.deleteById(id);}

    public Album createAlbum(Album newAlbum){return AlbumRepository.save(newAlbum);}

    public Album updateAlbum(long id, Album updatedAlbum){
        Optional<Album> AlbumToUpdateOptional = AlbumRepository.findById(id);

        if (AlbumToUpdateOptional.isPresent()){
            Album AlbumToUpdate = AlbumToUpdateOptional.get();

            AlbumToUpdate.setTitle(updatedAlbum.getTitle());
            AlbumToUpdate.setArtist(updatedAlbum.getArtist());
            AlbumToUpdate.setReleaseYear(updatedAlbum.getReleaseYear());
            AlbumToUpdate.setNumberOfSongs(updatedAlbum.getNumberOfSongs());
            AlbumToUpdate.setGenre(updatedAlbum.getGenre());
            AlbumToUpdate.setListOfSongs(updatedAlbum.getListOfSongs());

            return AlbumRepository.save(AlbumToUpdate);
        }

        return null;
    }
}
