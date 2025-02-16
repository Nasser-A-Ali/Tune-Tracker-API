package com.keyin.rest.artist;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findByName(String name);
    List<Artist> findByDebutYear(int year);
    List<Artist> findByGenre(String genre);
    List<Artist> findByCountry(String country);
}
