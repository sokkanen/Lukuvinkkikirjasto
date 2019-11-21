
package dtt.lukuvinkkikirjasto.demo.domain;

import javax.persistence.Entity;

/**
 *
 * @author milla
 */
@Entity
public class Book {
    
    private String author;
    private String title;
    private String isbn;
    private boolean read;
    
    
    public Book(String author, String title, String isbn, boolean read) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.read = read;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

   
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}
