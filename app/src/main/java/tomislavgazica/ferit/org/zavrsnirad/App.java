package tomislavgazica.ferit.org.zavrsnirad;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import tomislavgazica.ferit.org.zavrsnirad.orderManager.OrderManager;

public class App extends Application {

    private static FirebaseAuth auth;
    private static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        OrderManager.getInstance();

    }

    public static FirebaseAuth getFirebaseAuth(){
        return auth;
    }

    public static FirebaseDatabase getFirebaseDatabase(){
        return database;
    }

    public static Application getApplication(){
        return getApplication();
    }

}
