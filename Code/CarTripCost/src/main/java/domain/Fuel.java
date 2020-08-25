package domain;

import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class Fuel {

    private FuelType type;
    private double price;
    private Date priceUpdateDate;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPriceUpdateDate() {
        return priceUpdateDate;
    }

    public void setPriceUpdateDate(Date priceUpdateDate) {
        this.priceUpdateDate = priceUpdateDate;
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
