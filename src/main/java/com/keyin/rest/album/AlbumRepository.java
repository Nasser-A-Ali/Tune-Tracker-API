package com.keyin.rest.album;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    public List<Album> findByTitle(String title);
    public List<Album> findByReleaseYear(int releaseYear);
    public List<Album> findByGenre(String genre);
    public List<Album> findByArtistId(long artistId);
}