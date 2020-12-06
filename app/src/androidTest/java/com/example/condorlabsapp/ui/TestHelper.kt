package com.example.condorlabsapp.ui

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import junit.framework.TestCase
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object TestHelper {

    const val MOCK_SERVER_PORT = 8080

    //region testing files
    const val FILE_SOCCER_TEAMS= "soccer_teams.json"


    fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)

    /**
     * taken from https://gist.github.com/baconpat/8405a88d04bd1942eb5e430d33e4faa2
     * license MIT
     */
    class RecyclerViewMatcher(private val recyclerViewId: Int) {

        fun atPosition(position: Int): Matcher<View> {
            return atPositionOnView(position, -1)
        }

        private fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                var resources: Resources? = null
                var childView: View? = null

                override fun describeTo(description: Description) {
                    var idDescription = Integer.toString(recyclerViewId)
                    if (this.resources != null) {
                        idDescription = try {
                            this.resources!!.getResourceName(recyclerViewId)
                        } catch (var4: Resources.NotFoundException) {
                            "$recyclerViewId (resource name not found)"
                        }
                    }

                    description.appendText("RecyclerView with id: $idDescription at position: $position")
                }

                public override fun matchesSafely(view: View): Boolean {

                    this.resources = view.resources

                    if (childView == null) {
                        val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                        if (recyclerView?.id == recyclerViewId) {
                            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                            childView = viewHolder?.itemView
                        } else {
                            return false
                        }
                    }

                    return if (targetViewId == -1) {
                        view === childView
                    } else {
                        val targetView = childView?.findViewById<View>(targetViewId)
                        view === targetView
                    }
                }
            }
        }
    }


    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

        override fun check(view: View , noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            TestCase.assertEquals(expectedCount, recyclerView.adapter?.itemCount)
        }
    }

}