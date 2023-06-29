package spastore.unisa.esercitazioni.esame6_libri;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;

    public void Book() {
    }

    public void Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
