package domain;

import java.awt.Point;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Toll {

    private Point coordinates;
    private String name;

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Toll() {
    }

}
