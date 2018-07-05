package tomislavgazica.ferit.org.zavrsnirad.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private String tableId;
    private List<String> orderedItemsIds = new ArrayList<>();

    public Order() {
        id = UUID.randomUUID().toString();
    }

    public Order(String tableId, List<String> orderedItemsIds) {
        this.id = UUID.randomUUID().toString();
        this.tableId = tableId;
        this.orderedItemsIds.addAll(orderedItemsIds);
    }

    public Order(String id, String tableId, List<String> orderedItemsIds) {
        this.id = id;
        this.tableId = tableId;
        this.orderedItemsIds = orderedItemsIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public List<String> getOrderedItemsIds() {
        return orderedItemsIds;
    }

    public void setOrderedItemsIds(List<String> orderedItemsIds) {
        this.orderedItemsIds.addAll(orderedItemsIds);
    }

    public void setAllOrders(List<String> orders){
        this.orderedItemsIds.clear();
        this.orderedItemsIds.addAll(orders);
    }

    public void addItemToOrder(String id){
        orderedItemsIds.add(id);
    }

    public void removeAllOrders(){
        orderedItemsIds.clear();
    }

    public void removeItem(String id){
        for (int i = 0; i < orderedItemsIds.size(); i++){
            if (orderedItemsIds.get(i).equals(id)){
                orderedItemsIds.remove(i);
                break;
            }
        }
    }

    public void removeAllItems(String id){

        Iterator<String> itr = orderedItemsIds.iterator();

        while (itr.hasNext()){
            String number;
            number = itr.next();
            if (id.equals(number)){
                itr.remove();
            }
        }
    }
}
