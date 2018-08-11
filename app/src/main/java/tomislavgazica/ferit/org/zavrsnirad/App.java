package tomislavgazica.ferit.org.zavrsnirad;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;

public class App extends Application {

    private static App instance;
    private static FirebaseAuth auth;
    private static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        OrderManager.getInstance();

    }



}
