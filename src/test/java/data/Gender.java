package data;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Other("Other");

    public final String description;

    Gender(String description) {
        this.description = description;
    }
}