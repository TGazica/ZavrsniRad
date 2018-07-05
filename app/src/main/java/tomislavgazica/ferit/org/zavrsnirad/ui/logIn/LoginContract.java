package tomislavgazica.ferit.org.zavrsnirad.ui.logIn;

public interface LoginContract {

    interface View {

        void onUserSignedIn();

        void showNetworkError();

        void showUserInvalidError();

    }

    interface Presenter {

        void setView(LoginContract.View view);

        void signInUser(String email, String password);
    }

}
