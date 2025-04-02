package com.keyin.rest.artist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findByName(String name);

    List<Artist> findByDebutYear(Integer year);

    List<Artist> findByGenre(String genre);

    List<Artist> findByCountry(String country);
}
