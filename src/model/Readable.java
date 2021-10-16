package model;

import model.enumeration.ReadableType;

/**
 * @author Mahsa Alikhani m-58
 */
public class Readable extends Product{
    private ReadableType readableType;

    public ReadableType getReadableType() {
        return readableType;
    }

    public void setReadableType(ReadableType readableType) {
        this.readableType = readableType;
    }
}
