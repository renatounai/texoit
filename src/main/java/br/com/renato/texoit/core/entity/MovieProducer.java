package br.com.renato.texoit.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_producer")
public class MovieProducer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public MovieProducer() {
    }

    public MovieProducer(final Movie movie, final Producer producer) {
        this.movie = movie;
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieProducer that = (MovieProducer) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
