package domain;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public enum CarCategory {

    CAT_01("Cars, trucks (up to 8 seats, including driver) and other 2-axle vehicles without dual wheels, with a single-axle trailer."),
    CAT_02("Express buses (driver and one companion maximum), Micros, mini buses and tractor without semi-trailer."),
    CAT_03("2-axle vehicles with more than 4 wheels."),
    CAT_04("Bus with passengers"),
    CAT_05("Vehicles or cargo equipment with 3 axles."),
    CAT_06("4-axle vehicles or equipment without dual wheels."),
    CAT_07("Vehicles or cargo equipment with 4 or more axles with dual wheels");

    public final String description;

    private CarCategory(String categoryDescription) {
        this.description = categoryDescription;
    }
}
