package com.keyin.rest.song;

import com.keyin.rest.album.Album;
import com.keyin.rest.album.AlbumService;
import com.keyin.rest.artist.Artist;
import com.keyin.rest.artist.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SongControllerTest {

    @Mock
    private SongService songService;

    @Mock
    private ArtistService artistService;

    @Mock
    private AlbumService albumService;

    @InjectMocks
    private SongController songController;

    private Song testSong;
    private Artist testArtist;
    private Album testAlbum;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testArtist = new Artist();
        testArtist.setId(1L);
        testArtist.setName("Test Artist");
        testArtist.setDebutYear(2023);
        testArtist.setGenre("R&B");
        testArtist.setCountry("Canada");

        testAlbum = new Album();
        testAlbum.setId(1L);
        testAlbum.setTitle("Test Album");
        testAlbum.setReleaseYear(2025);
        testAlbum.setGenre("R&B");
        testAlbum.setArtist(testArtist);

        testSong = new Song();
        testSong.setId(1L);
        testSong.setTitle("Test Song");
        testSong.setDuration(180);
        testSong.setGenre("R&B");
        testSong.setReleaseYear(2025);
        testSong.setArtist(testArtist);
        testSong.setAlbums(Arrays.asList(testAlbum));
    }

    @Test
    void testGetAllSongs() {
        when(songService.getAllSongs()).thenReturn(Arrays.asList(testSong));

        List<Song> songs = songController.getAllSongs();

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    void testGetSongById() {
        when(songService.getSongById(1L)).thenReturn(testSong);

        Song song = songController.getSongById(1L);

        assertNotNull(song);
        assertEquals("Test Song", song.getTitle());
    }

    @Test
    void testGetSongByTitle() {
        when(songService.getSongByTitle("Test Song")).thenReturn(Arrays.asList(testSong));

        List<Song> songs = songController.getSongByTitle("Test Song", null, null, null);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    void testGetSongByGenre() {
        when(songService.getSongByGenre("R&B")).thenReturn(Arrays.asList(testSong));

        List<Song> songs = songController.getSongByTitle(null, "R&B", null, null);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    void testGetSongByReleaseYear() {
        when(songService.getSongByReleaseYear(2025)).thenReturn(Arrays.asList(testSong));

        List<Song> songs = songController.getSongByTitle(null, null, 2025, null);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    void testGetSongByArtistId() {
        when(songService.getSongByArtistId(1L)).thenReturn(Arrays.asList(testSong));

        List<Song> songs = songController.getSongByTitle(null, null, null, 1L);

        assertNotNull(songs);
        assertEquals(1, songs.size());
        assertEquals("Test Song", songs.get(0).getTitle());
    }

    @Test
    void testCreateSong() {
        when(songService.createSong(any(Song.class))).thenReturn(testSong);

        Song createdSong = songController.createSong(testSong);

        assertNotNull(createdSong);
        assertEquals("Test Song", createdSong.getTitle());
    }

    @Test
    void testUpdateSong() {
        when(songService.updateSong(1L, testSong)).thenReturn(testSong);

        ResponseEntity<Song> response = songController.updateSong(1L, testSong);

        assertNotNull(response.getBody());
        assertEquals("Test Song", response.getBody().getTitle());
    }

    @Test
    void testDeleteSong() {
        doNothing().when(songService).deleteSongById(1L);

        songController.deleteSongById(1L);

        verify(songService, times(1)).deleteSongById(1L);
    }
}