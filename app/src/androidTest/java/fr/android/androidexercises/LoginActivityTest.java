package fr.android.androidexercises;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    public LoginActivityTest() {
        //super(LoginActivity.class);
    }

    public void setUp() throws Exception {
        //super.setUp();
        //injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        //getActivity();
    }

    public void takeScreenshot(String name) {
        //Spoon.screenshot(getCurrentActivity(), name);
    }

    public Activity getCurrentActivity() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
        final Activity[] activity = new Activity[1];
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                ActivityLifecycleMonitor activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
                java.util.Collection<Activity> resumedActivities = activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(resumedActivities);
            }
        });
        return activity[0];
    }

    @Test
    public void checkLogin() throws Exception{
        Espresso.onView(ViewMatchers.withId(R.id.usernameEdit)).perform(ViewActions.typeText("b@xebia.fr"));
        Espresso.onView(ViewMatchers.withId(R.id.passwordEdit)).perform(ViewActions.typeText("password"));
        ViewActions.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.loggedText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}