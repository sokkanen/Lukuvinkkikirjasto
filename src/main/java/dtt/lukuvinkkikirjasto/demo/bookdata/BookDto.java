package dtt.lukuvinkkikirjasto.demo.bookdata;

import java.util.List;

public class BookDto {

    private String title;
    private List<String> authors;
    private List<String> languages;
    private List<String> libraries;
    private List<String> series;
    private List<String> subjects;

    public BookDto(
            String title,
            List<String> authors,
            List<String> languages,
            List<String> libraries,
            List<String> series,
            List<String> subjects

    ) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.libraries = libraries;
        this.series = series;
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getLibraries() {
        return libraries;
    }

    public List<String> getSeries() {
        return series;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}