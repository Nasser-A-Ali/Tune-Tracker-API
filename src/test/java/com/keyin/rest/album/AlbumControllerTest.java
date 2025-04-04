package com.keyin.rest.album;

import com.keyin.rest.artist.Artist;
import com.keyin.rest.artist.ArtistService;
import com.keyin.rest.song.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    @Mock
    private ArtistService artistService;

    @InjectMocks
    private AlbumController albumController;

    private Album testAlbum;
    private Artist testArtist;
    private Song testSong;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testArtist = new Artist();
        testArtist.setId(1L);
        testArtist.setName("Test Artist");
        testArtist.setDebutYear(2000);
        testArtist.setGenre("Rock");
        testArtist.setCountry("Canada");

        testSong = new Song();
        testSong.setId(1L);
        testSong.setTitle("Test Song");
        testSong.setDuration(180);
        testSong.setGenre("Rock");
        testSong.setReleaseYear(2020);
        testSong.setArtist(testArtist);

        testAlbum = new Album();
        testAlbum.setId(2L);
        testAlbum.setTitle("Test Album");
        testAlbum.setReleaseYear(2020);
        testAlbum.setGenre("Rock");
        testAlbum.setArtist(testArtist);
        testAlbum.setNumberOfSongs(1);
        testAlbum.setListOfSongs(Arrays.asList(testSong));
    }

    @Test
    void testGetAllAlbums() {
        when(albumService.getAllAlbums()).thenReturn(Arrays.asList(testAlbum));

        List<Album> albums = albumController.getAllAlbums();

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    void testGetAlbumById() {
        when(albumService.getAlbumById(1L)).thenReturn(testAlbum);

        Album album = albumController.getAlbumById(1L);

        assertNotNull(album);
        assertEquals("Test Album", album.getTitle());
    }

    @Test
    void testGetAlbumByTitle() {
        when(albumService.getAlbumByTitle("Test Album")).thenReturn(Arrays.asList(testAlbum));

        List<Album> albums = albumController.getAlbumByTitle("Test Album", null, null, null);

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    void testGetAlbumByReleaseYear() {
        when(albumService.getAlbumByReleaseYear(2020)).thenReturn(Arrays.asList(testAlbum));

        List<Album> albums = albumController.getAlbumByTitle(null, 2020, null, null);

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    void testGetAlbumByArtistId() {
        when(albumService.getAlbumByArtistId(1)).thenReturn(Arrays.asList(testAlbum));

        List<Album> albums = albumController.getAlbumByTitle(null, null, null, 1L);

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    void testGetAlbumByGenre() {
        when(albumService.getAlbumByGenre("Rock")).thenReturn(Arrays.asList(testAlbum));

        List<Album> albums = albumController.getAlbumByTitle(null, null, "Rock", null);

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.get(0).getTitle());
    }

    @Test
    void testCreateAlbum() {
        when(artistService.findById(1L)).thenReturn(testArtist);
        when(albumService.createAlbum(any(Album.class))).thenReturn(testAlbum);

        Album createdAlbum = albumController.createAlbum(testAlbum);

        assertNotNull(createdAlbum);
        assertEquals("Test Album", createdAlbum.getTitle());
    }

    @Test
    void testCreateAlbumArtistNotFound() {
        when(artistService.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            albumController.createAlbum(testAlbum);
        });
    }

    @Test
    void testUpdateAlbum() {
        when(albumService.updateAlbum(1L, testAlbum)).thenReturn(testAlbum);

        ResponseEntity<Album> response = albumController.updateAlbum(1L, testAlbum);

        assertNotNull(response.getBody());
        assertEquals("Test Album", response.getBody().getTitle());
    }

    @Test
    void testDeleteAlbum() {
        doNothing().when(albumService).deleteAlbumById(1L);

        albumController.deleteAlbumById(1L);

        verify(albumService, times(1)).deleteAlbumById(1L);
    }
}