package com.keyin.rest.song;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    public List<Song> findByTitle(String title);
    public List<Song> findByGenre(String genre);
    public List<Song> findByReleaseYear(Integer releaseYear);
    public List<Song> findByArtistId(Long artistId);
}
