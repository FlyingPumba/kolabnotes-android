package org.kore.kolabnotes.android;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.kore.kolabnotes.android.IsEqualTrimmingAndIgnoringCase.equalToTrimmingAndIgnoringCase;
import static org.kore.kolabnotes.android.VisibleViewMatcher.isVisible;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ETGTestCaseForPR {

  @Rule
  public ActivityTestRule<org.kore.kolabnotes.android.MainActivity> mActivityTestRule =
      new ActivityTestRule<>(org.kore.kolabnotes.android.MainActivity.class);

  @Test
  public void myTestCase() {
    System.out.println("Starting run of ETGTestCaseForPR");
    ViewInteraction root = onView(isRoot());
    root.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    ViewInteraction android_widget_TextView =
        onView(
            allOf(
                withId(R.id.action_search),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView.perform(getClickAction());

    ViewInteraction android_widget_ImageButton =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("Collapse")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageButton.perform(getClickAction());

    ViewInteraction root2 = onView(isRoot());
    root2.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    ViewInteraction android_widget_ImageView =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView.perform(getClickAction());

    ViewInteraction android_widget_LinearLayout =
        onView(
            allOf(
                classOrSuperClassesName(is("android.widget.LinearLayout")),
                isVisible(),
                hasDescendant(
                    allOf(
                        withId(R.id.title),
                        withTextOrHint(equalToTrimmingAndIgnoringCase("Export notebook"))))));
    android_widget_LinearLayout.perform(getClickAction());

    ViewInteraction android_widget_ImageView2 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView2.perform(getLongClickAction());

    ViewInteraction android_widget_TextView2 =
        onView(
            allOf(
                withId(R.id.tag_list),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView2.perform(getClickAction());

    ViewInteraction root3 = onView(isRoot());
    root3.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    ViewInteraction android_widget_ImageButton2 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("Navigate up")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar_tag_list),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton2.perform(getClickAction());

    ViewInteraction android_widget_TextView3 =
        onView(
            allOf(
                withId(R.id.tag_list),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView3.perform(getClickAction());

    ViewInteraction root4 = onView(isRoot());
    root4.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    ViewInteraction android_widget_ImageButton3 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("Navigate up")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar_tag_list),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton3.perform(getClickAction());

    ViewInteraction android_widget_ImageView3 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView3.perform(getClickAction());

    ViewInteraction android_widget_LinearLayout2 =
        onView(
            allOf(
                classOrSuperClassesName(is("android.widget.LinearLayout")),
                isVisible(),
                hasDescendant(
                    allOf(
                        withId(R.id.title),
                        withTextOrHint(equalToTrimmingAndIgnoringCase("Delete notebook"))))));
    android_widget_LinearLayout2.perform(getClickAction());

    ViewInteraction android_widget_ImageButton4 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("Navigate up")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageButton4.perform(getClickAction());

    ViewInteraction android_widget_ImageView4 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView4.perform(getLongClickAction());

    ViewInteraction android_widget_TextView4 =
        onView(
            allOf(
                withId(R.id.action_search),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView4.perform(getClickAction());

    ViewInteraction android_widget_TextView5 =
        onView(
            allOf(
                withId(R.id.tag_list),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView5.perform(getClickAction());

    ViewInteraction android_widget_ImageButton5 =
        onView(
            allOf(
                withId(R.id.addTagButton),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.tag_list_fragment),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton5.perform(getClickAction());

    Espresso.pressBackUnconditionally();

    ViewInteraction android_widget_ImageButton6 =
        onView(
            allOf(
                withId(R.id.addTagButton),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.tag_list_fragment),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton6.perform(getClickAction());

    onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_ENTER));

    onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_ENTER));

    ViewInteraction android_widget_ImageButton7 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("Navigate up")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar_tag_list),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton7.perform(getClickAction());

    ViewInteraction android_widget_ImageButton8 =
        onView(
            allOf(
                withId(R.id.fab_button),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.coordinator_overview),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.overview_fragment),
                                isDescendantOfA(
                                    allOf(
                                        withId(R.id.activity_main_frame),
                                        isDescendantOfA(withId(R.id.activity_main))))))))));
    android_widget_ImageButton8.perform(getClickAction());

    Espresso.pressBackUnconditionally();

    ViewInteraction android_widget_ImageButton9 =
        onView(
            allOf(
                withId(R.id.fab_button),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.coordinator_overview),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.overview_fragment),
                                isDescendantOfA(
                                    allOf(
                                        withId(R.id.activity_main_frame),
                                        isDescendantOfA(withId(R.id.activity_main))))))))));
    android_widget_ImageButton9.perform(getClickAction());

    ViewInteraction android_widget_EditText =
        onView(
            allOf(
                withId(R.id.dialog_text_input_field),
                withTextOrHint(equalToTrimmingAndIgnoringCase("Summary")),
                isVisible()));
    android_widget_EditText.perform(replaceText("vinificator"));

    Espresso.pressBackUnconditionally();

    onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_ENTER));

    ViewInteraction root5 = onView(isRoot());
    root5.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    ViewInteraction android_widget_ImageView5 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView5.perform(getLongClickAction());

    ViewInteraction android_widget_ImageView6 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView6.perform(getLongClickAction());

    ViewInteraction root6 = onView(isRoot());
    root6.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    ViewInteraction android_widget_TextView6 =
        onView(
            allOf(
                withId(R.id.tag_list),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView6.perform(getClickAction());

    Espresso.pressBackUnconditionally();

    ViewInteraction android_widget_ImageView7 =
        onView(
            allOf(
                withContentDescription(equalToTrimmingAndIgnoringCase("More options")),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_ImageView7.perform(getLongClickAction());

    ViewInteraction root7 = onView(isRoot());
    root7.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    ViewInteraction root8 = onView(isRoot());
    root8.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    ViewInteraction root9 = onView(isRoot());
    root9.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    ViewInteraction root10 = onView(isRoot());
    root10.perform(getSwipeAction(540, 897, 540, 1794));

    waitToScrollEnd();

    Espresso.pressBackUnconditionally();

    ViewInteraction android_widget_TextView7 =
        onView(
            allOf(
                withId(R.id.tag_list),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.toolbar),
                        isDescendantOfA(
                            allOf(
                                withId(R.id.activity_main_frame),
                                isDescendantOfA(withId(R.id.activity_main))))))));
    android_widget_TextView7.perform(getClickAction());

    ViewInteraction root11 = onView(isRoot());
    root11.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    ViewInteraction android_widget_ImageButton10 =
        onView(
            allOf(
                withId(R.id.addTagButton),
                isVisible(),
                isDescendantOfA(
                    allOf(
                        withId(R.id.tag_list_fragment),
                        isDescendantOfA(withId(R.id.activity_tag_list))))));
    android_widget_ImageButton10.perform(getClickAction());

    onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_ENTER));

    onView(isRoot()).perform(pressKey(KeyEvent.KEYCODE_ENTER));

    ViewInteraction root12 = onView(isRoot());
    root12.perform(getSwipeAction(540, 897, 540, 0));

    waitToScrollEnd();

    Espresso.pressBackUnconditionally();
  }

  private static Matcher<View> classOrSuperClassesName(final Matcher<String> classNameMatcher) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Class name or any super class name ");
        classNameMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        Class<?> clazz = view.getClass();
        String canonicalName;

        do {
          canonicalName = clazz.getCanonicalName();
          if (canonicalName == null) {
            return false;
          }

          if (classNameMatcher.matches(canonicalName)) {
            return true;
          }

          clazz = clazz.getSuperclass();
          if (clazz == null) {
            return false;
          }
        } while (!"java.lang.Object".equals(canonicalName));

        return false;
      }
    };
  }

  private static Matcher<View> withTextOrHint(final Matcher<String> stringMatcher) {
    return anyOf(withText(stringMatcher), withHint(stringMatcher));
  }

  private ViewAction getSwipeAction(
      final int fromX, final int fromY, final int toX, final int toY) {
    return ViewActions.actionWithAssertions(
        new GeneralSwipeAction(
            Swipe.SLOW,
            new CoordinatesProvider() {
              @Override
              public float[] calculateCoordinates(View view) {
                float[] coordinates = {fromX, fromY};
                return coordinates;
              }
            },
            new CoordinatesProvider() {
              @Override
              public float[] calculateCoordinates(View view) {
                float[] coordinates = {toX, toY};
                return coordinates;
              }
            },
            Press.FINGER));
  }

  private void waitToScrollEnd() {
    SystemClock.sleep(500);
  }

  private ClickWithoutDisplayConstraint getClickAction() {
    return new ClickWithoutDisplayConstraint(
        Tap.SINGLE,
        GeneralLocation.VISIBLE_CENTER,
        Press.FINGER,
        InputDevice.SOURCE_UNKNOWN,
        MotionEvent.BUTTON_PRIMARY);
  }

  private ClickWithoutDisplayConstraint getLongClickAction() {
    return new ClickWithoutDisplayConstraint(
        Tap.LONG,
        GeneralLocation.CENTER,
        Press.FINGER,
        InputDevice.SOURCE_UNKNOWN,
        MotionEvent.BUTTON_PRIMARY);
  }
}
