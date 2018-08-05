package com.akumar.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;

import com.android.bakingapp.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTesting {


    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = mActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void FullUITest() {



        onView(nthChildOf(withId(R.id.recipeRecyclerView), 0)).check(matches(hasDescendant(withText("Nutella Pie"))));
        onView(nthChildOf(withId(R.id.recipeRecyclerView), 1)).check(matches(hasDescendant(withText("Brownies"))));

        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(nthChildOf(withId(R.id.stepRecyclerView), 0)).check(matches(hasDescendant(withText("1 Recipe Introduction"))));
        onView(withId(R.id.stepRecyclerView))

                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView((withId(R.id.recipe_short))).check(matches(withText("Recipe Introduction")));
        onView(withId(R.id.next_step_btn)).perform(click());
        onView((withId(R.id.recipe_short))).check(matches(withText("Starting prep")));


    }


    public static Matcher<View> nthChildOf(final Matcher<View> matcher, final int i) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            public boolean matchesSafely(View v) {
                if (!(v.getParent() instanceof ViewGroup)) {
                    return matcher.matches(v.getParent());
                }

                ViewGroup viewGroup = (ViewGroup) v.getParent();
                return v.equals(viewGroup.getChildAt(i)) &&  matcher.matches(v.getParent()) ;
            }
        };
    }


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null)
            Espresso.unregisterIdlingResources(idlingResource);
    }

}
