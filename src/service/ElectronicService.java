package service;

import repository.ElectronicDao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
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

    public void addNewOrderToList(String ItemName, int quantity) throws Exception {
        int counter = 1;
        if(counter <= 5){
            Map<Integer, Integer> item = electronicDao.findItemIdPriceByName(ItemName);
            int itemId = -1; //////////////////?????exception???
            for (Integer key: item.keySet()) {
                itemId = key;
            }
            int price = item.get(itemId);
            int totalPrice = price * quantity;
            electronicDao.saveNewItem(itemId, quantity, totalPrice);
            counter++;
        }else{
            throw new Exception("Your shopping bag is full!\n1. Confirm\n2. remove some items");
        }

    }

}
