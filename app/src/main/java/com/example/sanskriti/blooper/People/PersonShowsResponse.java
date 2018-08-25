
package com.example.sanskriti.blooper.People;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class PersonShowsResponse {

    @Expose
    private List<PersonShows> cast;
    @Expose
    private List<PersonShows> crew;
    @Expose
    private Long id;

    public PersonShowsResponse(List<PersonShows> cast, List<PersonShows> crew, Long id) {
        this.cast = cast;
        this.crew = crew;
        this.id = id;
    }

    public List<PersonShows> getCast() {
        return cast;
    }

    public void setCast(List<PersonShows> cast) {
        this.cast = cast;
    }

    public List<PersonShows> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonShows> crew) {
        this.crew = crew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
