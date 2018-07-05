package tomislavgazica.ferit.org.zavrsnirad.model;

import java.util.UUID;

public class Table {

    private String id;
    private int tableNumber;
    private boolean isLoggedIn;

    public Table() {
    }

    public Table(int tableNumber) {
        this.id = UUID.randomUUID().toString();
        this.tableNumber = tableNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
