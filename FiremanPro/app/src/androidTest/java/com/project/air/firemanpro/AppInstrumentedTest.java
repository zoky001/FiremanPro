package com.project.air.firemanpro;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.air.firemanpro.profil.ProfilNewActivity;
import com.project.test.database.controllers.HouseController;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppInstrumentedTest {




    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.project.air.firemanpro", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureMainActivityisCorrect() throws Exception {
        MainActivity activity = rule.getActivity();
        View searchTextBox = activity.findViewById(R.id.til_autocompleteWithLabel);
        assertThat(searchTextBox,instanceOf(TextInputLayout.class));
        View buttonSearch = activity.findViewById(R.id.buttonSearching);
        assertThat(buttonSearch,instanceOf(Button.class));

    }

   /* @Rule
    public ActivityTestRule<ProfilNewActivity> rule1  = new  ActivityTestRule<ProfilNewActivity>(ProfilNewActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            FlowManager.init(new FlowConfig.Builder(InstrumentationRegistry.getTargetContext()).build());

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("EXTRA_SESSION_ID", "214");
            return intent;
        }
    };

    @Test
    public void ensureProfilNewActivity() throws Exception {
        ProfilNewActivity activity = rule1.getActivity();

        FlowManager.init(new FlowConfig.Builder(activity).build());

        View ownerName = activity.findViewById(R.id.textViewNameOwner);

        assertThat(ownerName,instanceOf(TextView.class));
        ownerName = (TextView) ownerName;

        assertThat(((TextView) ownerName).getText().toString(),is(HouseController.getFirstHouse().getName_owner()));

      //  View buttonSearch = activity.findViewById(R.id.buttonSearching);
      //  assertThat(buttonSearch,instanceOf(Button.class));

    }
*/

    private String mStringToBetyped;

 /*   @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(mStringToBetyped)));
    }
*/


}
