package dev.jayvijaygohil.nbateamsviewer.testdoubles

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.SortTeamsUseCase.SortType
import io.reactivex.Single

class SortTeamsUseCaseStub(
    val mock: SortTeamsUseCase = mock()
) : SortTeamsUseCase by mock {
    lateinit var list: List<Team>
    lateinit var sortType: SortType

    var isError = false

    override fun execute(list: List<Team>, sortType: SortType): Single<List<Team>> {
        return if (isError) {
            mock.execute(list, sortType)
            Single.error(NullPointerException())
        } else {
            Single.just(list).also {
                this.list = list
                this.sortType = sortType
                mock.execute(list, sortType)
            }
        }
    }

    fun verifyExecuteCalled(timesCalled: Int, list: List<Team>, sortType: SortType) {
        verify(mock, times(timesCalled)).execute(list, sortType)
    }

    fun verifyNoNewInteractions() {
        verifyNoMoreInteractions(mock)
    }
}