package service;

import repository.ElectronicDao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Mahsa Alikhani m-58
 */
public class ElectronicService {
    private final ElectronicDao electronicDao;

    public ElectronicService() throws SQLException, ClassNotFoundException {
        electronicDao = new ElectronicDao();
    }

    public void addNewOrder(String ItemName, int quantity) throws Exception {
        Map<Integer, Integer> item = electronicDao.findItemIdPriceByName(ItemName);
        int itemId = -1; //////////////////?????exception???
        for (Integer key: item.keySet()) {
            itemId = key;
        }
        int price = item.get(itemId);
        electronicDao.saveNewItem(itemId, quantity, price);
    }
}
