package tomislavgazica.ferit.org.zavrsnirad.presentation;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tomislavgazica.ferit.org.zavrsnirad.ui.logIn.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private FirebaseAuth auth;

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInUser(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                view.onUserSignedIn();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.showNetworkError();
            }
        });
    }
}
