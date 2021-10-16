package model;

import model.enumeration.ShoeType;

/**
 * @author Mahsa Alikhani m-58
 */
public class Shoe extends Product{
    private ShoeType shoeType;

    public ShoeType getShoeType() {
        return shoeType;
    }

    public void setShoeType(ShoeType shoeType) {
        this.shoeType = shoeType;
    }
}
