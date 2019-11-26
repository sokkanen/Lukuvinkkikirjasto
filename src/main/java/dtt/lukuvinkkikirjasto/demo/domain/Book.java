
package dtt.lukuvinkkikirjasto.demo.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author milla
 */
public class Book {

    @Size(min = 2, max = 30, message="Title must be between 2 and 30 characters")
    private String title;
    
    @Size(min = 2, max = 30, message="Author name must be between 2 and 30 characters")
    private String author;
    
    
    @Size(min = 10, max = 13, message="ISBN length must be between 10 and 13 ")
    private String isbn;
    private boolean read;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.read = false;
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
