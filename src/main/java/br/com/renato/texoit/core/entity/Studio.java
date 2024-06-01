package br.com.renato.texoit.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Studio() {
    }

    public Studio(final String name) {
        this.name = name.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Studio studio = (Studio) o;
        return id == studio.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
