package tigran.applications.newssimpleapp

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import tigran.applications.newssimpleapp.ui.MainActivity
import tigran.applications.newssimpleapp.ui.NewsAdapter
import tigran.applications.newssimpleapp.ui.uistate.NewsUiState


@RunWith(AndroidJUnit4::class)
class NewsListDisplayedUiTest {

    private lateinit var newsAdapter: NewsAdapter

    private val mockNewsList = listOf(
        NewsUiState(id = "1", webTitle = "Breaking News 1", sectionName = "Content here"),
        NewsUiState(id = "2", webTitle = "Breaking News 2", sectionName = "More content"),
        NewsUiState(id = "3", webTitle = "Breaking News 3", sectionName = "Even more content")
    )

    @Test
    fun recyclerView_displaysMockedNews() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        ActivityScenario.launch<MainActivity>(intent).use { scenario ->
            scenario.onActivity { activity ->
                newsAdapter = NewsAdapter()
                val recyclerView = activity.findViewById<RecyclerView>(R.id.rv_news)
                recyclerView.adapter = newsAdapter

                newsAdapter.addItems(mockNewsList)

                activity.runOnUiThread {
                    newsAdapter.notifyDataSetChanged()
                }
            }

            mockNewsList.forEach { news ->
                onView(withText(news.webTitle))
                    .check(matches(isDisplayed()))
            }
        }
    }
}
