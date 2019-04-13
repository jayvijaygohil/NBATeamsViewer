package dev.jayvijaygohil.nbateamsviewer.testdoubles

import dev.jayvijaygohil.nbateamsviewer.usecase.ShowAllTeamsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.jayvijaygohil.nbateamsviewer.model.Team
import io.reactivex.Single

class ShowAllTeamsUseCaseStub(
    val mock: ShowAllTeamsUseCase = mock()
): ShowAllTeamsUseCase by mock {
    val list: List<Team> = emptyList()
    var isError = false

    override fun execute(): Single<List<Team>> {
        return if (isError) {
            mock.execute()
            Single.error(NullPointerException())
        } else {
            Single.just(list).also {
                mock.execute()
            }
        }
    }

    fun verifyExecuteCalled(timesCalled: Int) {
        verify(mock, times(timesCalled)).execute()
    }

    fun verifyNoNewInteractions() {
        verifyNoMoreInteractions(mock)
    }
}