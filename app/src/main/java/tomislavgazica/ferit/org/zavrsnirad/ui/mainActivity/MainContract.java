package tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity;

public interface MainContract {
    interface View{
        void onDataUpdated();
    }

    interface Presenter{
        void setView(MainContract.View view);
    }
}
