package service;

import repository.ElectronicDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicService {
    private final ElectronicDao electronicDao;

    public ElectronicService() throws SQLException, ClassNotFoundException {
        electronicDao = new ElectronicDao();
    }



}
