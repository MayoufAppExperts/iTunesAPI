package com.example.mohammed.itunesapi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by TheAppExperts on 11/10/2017.
 */

public class PopFragmentTest {

    @Rule
    public FragmentTestRule<PopFragment> popFragmentFragmentTestRule = new FragmentTestRule<>(PopFragment.class);

    @Before
    public void setUp() throws Exception{
        popFragmentFragmentTestRule.launchActivity(null);
    }

    @Test
    public void instantiateFragment (){
        onView(withId(R.id.content)).check(matches(isDisplayed()));
    }
    @Test
    public void testRecyclerView(){

        onView(withId(R.id.pop_recycler_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.pop_recycler_view)).perform(scrollToPosition(10))
                .check(matches(isDisplayed()));
        onView(withId(R.id.pop_recycler_view)).perform(scrollToPosition(4));
        //Espresso seems to need some time before it can register an item within the
        //Recycler View, thus the need to putt the thread to sleep after scrolling to a position
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Adele")).check(matches(isDisplayed()));
    }
}
