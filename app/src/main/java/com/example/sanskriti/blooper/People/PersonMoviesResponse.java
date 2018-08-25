
package com.example.sanskriti.blooper.People;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class PersonMoviesResponse {

    @Expose
    private List<PersonMovies> cast;
    @Expose
    private List<PersonMovies> crew;
    @Expose
    private Long id;

    public PersonMoviesResponse(List<PersonMovies> cast, List<PersonMovies> crew, Long id) {
        this.cast = cast;
        this.crew = crew;
        this.id = id;
    }

    public List<PersonMovies> getCast() {
        return cast;
    }

    public void setCast(List<PersonMovies> cast) {
        this.cast = cast;
    }

    public List<PersonMovies> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonMovies> crew) {
        this.crew = crew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
