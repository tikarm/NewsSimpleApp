package tigran.applications.newssimpleapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tigran.applications.newssimpleapp.NewsSimpleAppApplication
import tigran.applications.newssimpleapp.data.local.datasource.LocalDataSource
import tigran.applications.newssimpleapp.data.remote.api.ApiClient
import tigran.applications.newssimpleapp.data.remote.datasource.RemoteDataSource
import tigran.applications.newssimpleapp.data.repository.GetNewsRepository
import tigran.applications.newssimpleapp.databinding.ActivityMainBinding
import tigran.applications.newssimpleapp.domain.usecase.GetNewsUseCase
import tigran.applications.newssimpleapp.ui.uistate.ScreenProgressState

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory(
            GetNewsUseCase(
                GetNewsRepository(
                    remoteDataSource = RemoteDataSource(ApiClient.api),
                    localDataSource = LocalDataSource(
                        (application as NewsSimpleAppApplication).database.newsDao()
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        observeData()

        viewModel.getNews()
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()

        recyclerView = binding.rvNews
        recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager
            adapter = newsAdapter

            val dividerItemDecoration = DividerItemDecoration(
                this@MainActivity,
                linearLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
        }


    }

    private fun observeData() {
        viewModel.screenUiState.observe(this) { state ->
            when (val progressState = state.screenProgressState) {
                is ScreenProgressState.Loading -> {
                    binding.tvErrorMessage.isVisible = false
                    binding.progress.isVisible = true
                }

                is ScreenProgressState.Error -> {
                    binding.progress.isVisible = false
                    binding.tvErrorMessage.isVisible = true
                    binding.tvErrorMessage.text = progressState.message
                }

                is ScreenProgressState.Loaded -> {
                    binding.progress.isVisible = false
                    binding.tvErrorMessage.isVisible = false

                    newsAdapter.addItems(state.newsUiStateList)
                }
            }
        }
    }
}