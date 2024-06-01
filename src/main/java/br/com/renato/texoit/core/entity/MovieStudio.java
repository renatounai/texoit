package br.com.renato.texoit.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_studio")
public class MovieStudio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public MovieStudio() {
    }

    public MovieStudio(final Movie movie, final Studio studio) {
        this.movie = movie;
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieStudio that = (MovieStudio) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
