package tigran.applications.newssimpleapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tigran.applications.newssimpleapp.databinding.ItemNewsBinding
import tigran.applications.newssimpleapp.ui.uistate.NewsUiState


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList = ArrayList<NewsUiState>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsUiState) {
            binding.tvTitleItemNews.text = news.webTitle
            binding.tvCategoryItemNews.text = news.sectionName
        }
    }


    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addItems(news: List<NewsUiState>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }
}