package service;

import model.Electronic;
import repository.ElectronicDao;

import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicService {
    private final ElectronicDao electronicDao;

    public ElectronicService() throws SQLException, ClassNotFoundException {
        electronicDao = new ElectronicDao();
    }

    public void addNewOrder(Electronic electronic){

    }
}
