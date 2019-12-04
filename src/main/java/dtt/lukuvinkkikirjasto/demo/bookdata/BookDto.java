package dtt.lukuvinkkikirjasto.demo.bookdata;

public class BookDto {

    private String name;
    private Author[] authors;
    private String[] languages;
    private String[] libraries;
    private String[] categories;
    private String[] series;
    private String[] subjects;

    public BookDto(
            String name,
            Author[] authors,
            String[] languages,
            String[] libraries,
            String[] categories,
            String[] series,
            String[] subjects

    ) {
        this.name = name;
        this.authors = authors;
        this.languages = languages;
        this.libraries = libraries;
        this.categories = categories;
        this.series = series;
        this.subjects = subjects;
    }
}


class Author {
    public final String name;
    public final String role;

    public Author(String name, String role){
        this.name = name;
        this.role = role;
    }
}
