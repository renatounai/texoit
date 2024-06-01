package br.com.renato.texoit.core.dto;

import java.util.Objects;

public class MovieCSV {
    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(final String studios) {
        this.studios = studios;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(final String producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(final boolean winner) {
        this.winner = winner;
    }

    public MovieCSV() {
    }

    public MovieCSV(final Integer year, final String title, final String studios, final String producers, final Boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MovieCSV movieCSV = (MovieCSV) o;
        return year.equals(movieCSV.year) && title.equals(movieCSV.title) && studios.equals(movieCSV.studios) && producers.equals(movieCSV.producers) && Objects.equals(winner, movieCSV.winner);
    }

    @Override
    public int hashCode() {
        int result = year.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + studios.hashCode();
        result = 31 * result + producers.hashCode();
        result = 31 * result + Objects.hashCode(winner);
        return result;
    }

    @Override
    public String toString() {
        return "MovieCSV{" +
                "year=" + year +
                ", title='" + title + '\'' +
                ", studios='" + studios + '\'' +
                ", producers='" + producers + '\'' +
                ", winner=" + winner +
                '}';
    }
}
