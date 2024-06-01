package br.com.renato.texoit.core.entity;

import br.com.renato.texoit.core.dto.MovieCSV;
import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "year_release")
    private int year;

    @Column(name = "title")
    private String title;

    @Column(name = "winner")
    private boolean winner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Movie() {
    }

    public Movie(MovieCSV movieCSV) {
        this.year = movieCSV.getYear();
        this.title = movieCSV.getTitle();
        this.winner = movieCSV.isWinner();
    }

    public Movie(final int id, final int year, final String title, final boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}