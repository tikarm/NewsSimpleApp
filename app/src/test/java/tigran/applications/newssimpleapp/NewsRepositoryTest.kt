package tigran.applications.newssimpleapp

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tigran.applications.newssimpleapp.data.local.datasource.LocalDataSource
import tigran.applications.newssimpleapp.data.local.entity.NewsEntity
import tigran.applications.newssimpleapp.data.remote.data.NewsDto
import tigran.applications.newssimpleapp.data.remote.data.Response
import tigran.applications.newssimpleapp.data.remote.datasource.RemoteDataSource
import tigran.applications.newssimpleapp.data.remote.exception.ApiException
import tigran.applications.newssimpleapp.data.repository.GetNewsRepository
import tigran.applications.newssimpleapp.domain.model.NewsModel
import tigran.applications.newssimpleapp.domain.repository.NewsRepository

class NewsRepositoryTest {
    private lateinit var newsRepository: NewsRepository
    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()

    @Before
    fun setup() {
        newsRepository = GetNewsRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `test getNews returns news when successful`() = runTest {
        val mockNewsDto = NewsDto(id = "1", webTitle = "Title", sectionName = "Content")
        val mockNewsModel = NewsModel(id = "1", webTitle = "Title", sectionName = "Content")
        val mockApiResponse = Response(news = listOf(mockNewsDto))
        val mockNewsEntity = NewsEntity(id = "1", webTitle = "Title", sectionName = "Content")

        coEvery { remoteDataSource.getNews() } returns mockApiResponse.news!!
        coEvery { localDataSource.insertNews(any()) } returns Unit
        coEvery { localDataSource.getNews() } returns listOf(mockNewsEntity)

        val result = newsRepository.getNews()

        assert(result.isNotEmpty())
        assertEquals(mockNewsModel.id, result[0].id)
        assertEquals(mockNewsModel.webTitle, result[0].webTitle)
        assertEquals(mockNewsModel.sectionName, result[0].sectionName)

        coVerify { remoteDataSource.getNews() }
        coVerify { localDataSource.insertNews(any()) }
        coVerify { localDataSource.getNews() }
    }

    @Test
    fun `test getNews throws ApiException when remote data is empty`() = runTest {
        coEvery { remoteDataSource.getNews() } throws ApiException("Response is Empty")

        try {
            newsRepository.getNews()
        } catch (e: ApiException) {
            assertEquals(e.message, "Response is Empty")
        }

        coVerify { remoteDataSource.getNews() }
        coVerify(exactly = 0) { localDataSource.insertNews(any()) }
        coVerify(exactly = 0) { localDataSource.getNews() }
    }
}