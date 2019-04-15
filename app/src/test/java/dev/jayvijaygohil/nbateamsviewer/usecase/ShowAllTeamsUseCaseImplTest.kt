package dev.jayvijaygohil.nbateamsviewer.usecase

import dev.jayvijaygohil.nbateamsviewer.domain.entity.Players
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.ShowAllTeamsUseCase
import dev.jayvijaygohil.nbateamsviewer.domain.usecase.ShowAllTeamsUseCaseImpl
import dev.jayvijaygohil.nbateamsviewer.testdoubles.ScoreServerRepositoryStub
import org.junit.Before
import org.junit.Test

class ShowAllTeamsUseCaseImplTest {
    private lateinit var usecase: ShowAllTeamsUseCase
    private lateinit var repository: ScoreServerRepositoryStub

    @Before
    fun setup() {
        repository = ScoreServerRepositoryStub()
        usecase = ShowAllTeamsUseCaseImpl(repository)
    }

    @Test
    fun `test repository_getAllTeams is invoked`() {
        usecase.execute().test()

        repository.verifyGetAllTeamsCalled(1)
        repository.verifyNoNewInteractions()
    }

    @Test
    fun `test usecase sorts the teams and players according to their names in ASC order`() {
        val teamList = teamList()
        val expectedTeamList = sortedTeamList()

        repository.list = teamList

        usecase
            .execute()
            .test()
            .assertValueCount(1)
            .assertResult(expectedTeamList)
    }

    private fun teamList() = listOf(
        Team(
            teamName = "a",
            teamPlayers = listOf(Players(playerFirstName = "c"), Players(playerFirstName = "b"), Players(playerFirstName = "a"))
        ),
        Team(teamName = "d"),
        Team(teamName = "c"),
        Team(teamName = "b")
    )

    private fun sortedTeamList() = listOf(
        Team(
            teamName = "a",
            teamPlayers = listOf(Players(playerFirstName = "a"), Players(playerFirstName = "b"), Players(playerFirstName = "c"))
        ),
        Team(teamName = "b"),
        Team(teamName = "c"),
        Team(teamName = "d")
    )
}