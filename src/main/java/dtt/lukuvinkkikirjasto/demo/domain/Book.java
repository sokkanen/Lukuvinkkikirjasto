package dtt.lukuvinkkikirjasto.demo.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author milla
 */
public class Book {

    private int id;

    @Size(min = 2, max = 30, message = "Title must be between 2 and 30 characters")
    private String title;

    private String author;

    @Pattern(regexp = "^$|^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",message = "Invalid ISBN")
    private String isbn;
    private boolean read;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;

        if (this.validateIsbn10(isbn) == true || this.validateIsbn13(isbn) == true) {
            this.isbn = isbn;
        } else if (isbn == null) {
            this.isbn = "";
        } else {
            this.isbn = "error";
        }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean validateIsbn10(String isbn) {
        if (isbn == null) {
            return false;
        }
        String tempIsbn;
        //remove hyphens
        tempIsbn = isbn.replaceAll("-", "");

        //must be a 10 digit ISBN
        if (tempIsbn.length() != 10) {
            return false;
        }

        try {
            int tot = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Integer.parseInt(tempIsbn.substring(i, i + 1));
                tot += ((10 - i) * digit);
            }

            String checksum = Integer.toString((11 - (tot % 11)) % 11);
            if ("10".equals(checksum)) {
                checksum = "X";
            }

            return checksum.equals(tempIsbn.substring(9));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs
            return false;
        }
    }

    public boolean validateIsbn13(String isbn) {
        if (isbn == null) {
            return false;
        }
        String tempIsbn;
        //remove hyphens
        tempIsbn = isbn.replaceAll("-", "");

        //must be a 13 digit ISBN
        if (tempIsbn.length() != 13) {
            return false;
        }

        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(tempIsbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(tempIsbn.substring(12));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBN
            return false;
        }
    }
}




    

