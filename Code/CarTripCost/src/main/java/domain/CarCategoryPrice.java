package domain;

import java.util.Date;

/**
 *
 * @author Federico De Luca (federicoNdeluca@gmail.com)
 */
public class CarCategoryPrice {

    private CarCategory category;
    private double price;
    private Date updateDate;

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public CarCategoryPrice() {
    }

}
