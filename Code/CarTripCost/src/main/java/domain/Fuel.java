package domain;

import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Fuel {

    private FuelType type;
    private double cost;
    private Date costUpdateDate;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCostUpdateDate() {
        return costUpdateDate;
    }

    public void setCostUpdateDate(Date costUpdateDate) {
        this.costUpdateDate = costUpdateDate;
    }

    public FuelType getType() {
        return type;
    }

    public void setType(FuelType type) {
        this.type = type;
    }

    public Fuel() {

    }
}
