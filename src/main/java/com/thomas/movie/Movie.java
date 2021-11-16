package com.thomas.movie;

import java.time.LocalDate;
import java.util.List;

import com.thomas.actor.Actor;

public record Movie(Integer id,
                    String name,
                    List<Actor> actors,
                    LocalDate releaseDate) {
}
