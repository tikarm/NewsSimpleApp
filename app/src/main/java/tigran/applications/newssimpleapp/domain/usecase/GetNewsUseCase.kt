package tigran.applications.newssimpleapp.domain.usecase

import tigran.applications.newssimpleapp.data.remote.exception.ApiException
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.repository.NewsRepository

class GetNewsUseCase(
    private val repository: NewsRepository,
) {
    @Throws(ApiException::class)
    suspend operator fun invoke(): List<NewsModel> {
        return repository.getNews()
    }
}