package com.thomas.movie;

import com.thomas.movie.MovieController;
import com.thomas.movie.MovieService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    private MovieController underTest;

    @BeforeEach
    void setUp() {
        underTest = new MovieController(movieService);
    }
}