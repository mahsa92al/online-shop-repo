package model;

import model.enumeration.ElectronicTypes;

/**
 * @author Mahsa Alikhani m-58
 */
public class Electronic extends Product{
    private ElectronicTypes electronicTypes;

    public ElectronicTypes getElectronicTypes() {
        return electronicTypes;
    }

    public void setElectronicTypes(ElectronicTypes electronicTypes) {
        this.electronicTypes = electronicTypes;
    }
}
