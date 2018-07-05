package tomislavgazica.ferit.org.zavrsnirad.interfaces;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseCallbacks {
    void onNewItem(DataSnapshot dataSnapshot);
}
