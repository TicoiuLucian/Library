package library.entity;

public enum BookGenre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MISTERY("Mistery"),
    ROMANCE("Romance"),
    SCIFI("Scifi"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private final String displayEnum;

    BookGenre(String displayEnum) {
        this.displayEnum = displayEnum;
    }

    public String getDisplayEnum() {
        return displayEnum;
    }
}