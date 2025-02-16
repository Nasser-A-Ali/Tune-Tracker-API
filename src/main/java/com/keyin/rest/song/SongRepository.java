package com.keyin.rest.song;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    public Song findByTitle(String title);
    public Song findByArtistId(long artistId);
    public Song findByGenre(String genre);
}
