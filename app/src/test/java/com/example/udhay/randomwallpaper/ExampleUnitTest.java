package com.example.udhay.randomwallpaper;

import com.example.udhay.randomwallpaper.fragments.WallpapersFragment;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkEnum() {

        assertEquals(WallpapersFragment.WALLPAPERS_FRAGMENT_ACTIONS.NEW_WALLPAPER_DISPLAY.toString(), "NEW_COLLECTIONS_DISPLAY");
    }

    @Test
    public void checkDate() {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            System.out.print(format.parse("2018-11-03"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}