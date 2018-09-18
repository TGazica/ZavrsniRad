package tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity;

import java.util.List;

import tomislavgazica.ferit.org.zavrsnirad.model.Table;

public interface MainContract {
    interface View{
        void onDataUpdated();

        void setTables(List<Table> tables);
    }

    interface Presenter{
        void setView(MainContract.View view);

        void getTables();
    }
}
