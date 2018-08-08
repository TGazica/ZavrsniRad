package tomislavgazica.ferit.org.zavrsnirad.ui.logIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.presentation.LoginPresenter;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @BindView(R.id.loginEmail)
    EditText loginEmail;
    @BindView(R.id.loginPassword)
    EditText loginPassword;
    @BindView(R.id.loginButton)
    Button loginButton;

    private LoginContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter();
        presenter.setView(this);

        presenter.signInUser("tomislav.gazica@gmail.com", "tomagazz");
    }

    @OnClick(R.id.loginButton)
    public void loginUser(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if (!email.equals("") && !password.equals("") && !email.isEmpty() && !password.isEmpty()) {
            presenter.signInUser(email, password);
        }else {
            showUserInvalidError();
        }
    }

    @Override
    public void onUserSignedIn() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getApplicationContext(), R.string.no_internet_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserInvalidError() {
        Toast.makeText(getApplicationContext(), R.string.no_username_password_error, Toast.LENGTH_SHORT).show();
    }
}
