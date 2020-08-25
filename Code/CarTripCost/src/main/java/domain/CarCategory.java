package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public enum CarCategory {

    CAT_01("Car category 01"),
    CAT_02("Car category 03"),
    CAT_03("Car category 03");

    public final String description;

    private CarCategory(String categoryDescription) {
        this.description = categoryDescription;
    }
}
