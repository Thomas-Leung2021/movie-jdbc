package com.thomas.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    // dependency injection here so jdbcTemplate uses @Bean
    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        var sql = """
                SELECT id, name, release_date
                FROM movie
                LIMIT 100
                """;
        // you need sql and a row mapper (take each row from database and map it to a Movie object)
        // return = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
        //     return new Movie(
        //         resultSet.getInt("id"),
        //         resultSet.getString("name"),
        //         List.of(),
        //         LocalDate.parse(resultSet.getString("release_date"))
        //     );
        // });

        // after we move the above code to a new class
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    /**
     * {
     *  "name": "Avengers",
     *  "releaseDate": "2020-03-03"
     * }
     */
    @Override
    public int insertMovie(Movie movie) {
        // you can use String type as well
        var sql = """
                INSERT INTO movie (name, release_date)
                VALUES (?, ?)
                """;
        // update gets a sql statement and all "?" arguments
        // it returns the number of rows affected
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        // throw new UnsupportedOperationException("not implemented");
        var sql = """
                DELETE FROM movie
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        var sql = """
                SELECT id, name, release_date
                FROM movie
                WHERE id = ?
                """;
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), id);
        return movies.stream().findFirst();
    }
    
}
