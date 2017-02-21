package fr.android.androidexercises;

import android.view.View;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

/**
 * Created by mclaveau on 21/02/2017.
 */
@RunWith(CustomRobolectricTestRunner.class)
public class LoginPresenterTest2 {
    LoginActivity activity;

    @Before
    public void setUp() throws Exception{
        activity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void shouldBeLogged(){
        activity.logged();
        Assertions.assertThat(activity.loginLayout).hasVisibility(View.GONE);
    }


}
