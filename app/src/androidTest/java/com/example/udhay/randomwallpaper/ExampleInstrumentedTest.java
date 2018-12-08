package com.example.udhay.randomwallpaper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.EspressoKey;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.udhay.randomwallpaper.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getArguments;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.udhay.randomwallpaper", appContext.getPackageName());


    }

    @Test
    public void openSearch(){

        String searchQuery = "search";

        onView(withId(R.id.search_menu_item))
                .perform(click());

        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText(searchQuery))
                .perform(pressImeActionButton())
                .check(matches(allOf(withId(android.support.design.R.id.search_src_text) , withText(searchQuery))));

    }

    @Test
    public void clickRecyclerItem(){

        onView(withId(R.id.featured_images_recycler_view))
                //.perform(RecyclerViewActions.scrollToPosition(20))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3 , click()))
                .check(matches(allOf(withId(R.id.photo_image_view) , isDisplayed())));
    }


    @Test
    public void clickCollection(){

        onView(withId(R.id.view_pager))
                .perform(swipeRight());

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(9));
    }
}
