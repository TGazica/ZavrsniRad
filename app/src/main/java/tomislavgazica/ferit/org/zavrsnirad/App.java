package tomislavgazica.ferit.org.zavrsnirad;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import tomislavgazica.ferit.org.zavrsnirad.model.Table;
import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;

public class App extends Application {

    private static App instance;
    private static FirebaseAuth auth;
    private static FirebaseDatabase database;
    private static Table table;
    private static boolean isTableSetUp = false;
    private static String lang = Constants.CROATIAN_LANGUAGE;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        OrderManager.getInstance();

    }

    public static Table getTable() {
        return table;
    }

    public static void setTable(Table table) {
        App.table = table;
    }

    public static boolean isTableSetUp() {
        return isTableSetUp;
    }

    public static void setTableSetUp(boolean tableSetUp) {
        isTableSetUp = tableSetUp;
    }

    public static String getLang() {
        return lang;
    }

    public static void setLang(String lang) {
        App.lang = lang;
    }
}
