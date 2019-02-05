package com.example.udhay.randomwallpaper;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.EspressoKey;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.udhay.randomwallpaper.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getArguments;
import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class , true , true);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.udhay.randomwallpaper", appContext.getPackageName());

    }

    @Test
    public void imageClick_showDetailImage(){

        onView(withId(R.id.featured_images_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3 , click()));

        onView(withId(R.id.photo_image_view))
                .check(matches(isDisplayed()));

    }


    @Test
    public void search_keywordSearched_showResults(){

        String searchQuery = "search";

        onView(withId(R.id.search_menu_item))
                .perform(click());

        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText(searchQuery))
                .perform(pressImeActionButton())
                .check(matches(allOf(withId(android.support.design.R.id.search_src_text) , withText(searchQuery))));

    }




    //@Test
    public void clickCollection(){

        onView(withId(R.id.view_pager))
                .perform(swipeRight());

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(9));
    }

    @Test
    public void scrollImages_scrollEndlessly(){

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(20));
    }
}
