package com.keyin.rest.artist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArtistControllerTest {

    @Mock
    private ArtistService artistService;

    @InjectMocks
    private ArtistController artistController;

    private Artist testArtist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testArtist = new Artist();
        testArtist.setId(1L);
        testArtist.setName("Test Artist");
        testArtist.setDebutYear(2000);
        testArtist.setGenre("Rock");
        testArtist.setCountry("Canada");
    }

    @Test
    void testGetArtists() {
        List<Artist> artists = Arrays.asList(testArtist);
        when(artistService.findAll()).thenReturn(artists);

        List<Artist> result = artistController.getArtists();

        assertEquals(1, result.size());
        assertEquals("Test Artist", result.get(0).getName());
    }

    @Test
    void testGetArtistById() {
        when(artistService.findById(1L)).thenReturn(testArtist);

        Artist result = artistController.getArtistById(1L);

        assertEquals("Test Artist", result.getName());
    }

    @Test
    void testSearchArtistsByDebutYear() {
        List<Artist> artists = Arrays.asList(testArtist);
        when(artistService.findByDebutYear(2000)).thenReturn(artists);

        List<Artist> result = artistController.searchArtists(null, 2000, null);

        assertEquals(1, result.size());
        assertEquals("Test Artist", result.get(0).getName());
    }

    @Test
    void testSearchArtistsByGenre() {
        List<Artist> artists = Arrays.asList(testArtist);
        when(artistService.findByGenre("Rock")).thenReturn(artists);

        List<Artist> result = artistController.searchArtists(null, null, "Rock");

        assertEquals(1, result.size());
        assertEquals("Test Artist", result.get(0).getName());
    }

    @Test
    void testCreateArtist() {
        when(artistService.createArtist(any(Artist.class))).thenReturn(testArtist);

        Artist result = artistController.createArtist(testArtist);

        assertEquals("Test Artist", result.getName());
    }

    @Test
    void testUpdateArtist() {
        when(artistService.updateArtist(anyLong(), any(Artist.class))).thenReturn(testArtist);

        Artist result = artistController.updateArtist(1L, testArtist);

        assertEquals("Test Artist", result.getName());
    }

    @Test
    void testDeleteArtist() {
        doNothing().when(artistService).deleteArtistById(1L);

        artistController.deleteArtistById(1L);

        verify(artistService, times(1)).deleteArtistById(1L);
    }
}