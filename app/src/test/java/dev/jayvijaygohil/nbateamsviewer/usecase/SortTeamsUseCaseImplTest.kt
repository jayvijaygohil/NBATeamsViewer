package dev.jayvijaygohil.nbateamsviewer.usecase

import dev.jayvijaygohil.nbateamsviewer.model.Team
import dev.jayvijaygohil.nbateamsviewer.usecase.SortTeamsUseCase.SortType.*
import org.junit.Before
import org.junit.Test

class SortTeamsUseCaseImplTest {
    private lateinit var usecase: SortTeamsUseCase

    @Before
    fun setUp() {
        usecase = SortTeamsUseCaseImpl()
    }

    @Test
    fun `test usecase sorts the teams according to their names in ASC order`() {
        val teamList = teamList()
        val expectedTeamList = ascNameTeamList()

        usecase
            .execute(teamList, NAME_ASC)
            .test()
            .assertResult(expectedTeamList)
    }

    @Test
    fun `test usecase sorts the teams according to their names in DESC order`() {
        val teamList = teamList()
        val expectedTeamList = descNameTeamList()

        usecase
            .execute(teamList, NAME_DESC)
            .test()
            .assertResult(expectedTeamList)
    }

    @Test
    fun `test usecase sorts the teams according lowest wins`() {
        val teamList = teamList()
        val expectedTeamList = lowestWinsTeamList()

        usecase
            .execute(teamList, LOWEST_WINS)
            .test()
            .assertResult(expectedTeamList)
    }

    @Test
    fun `test usecase sorts the teams according highest wins`() {
        val teamList = teamList()
        val expectedTeamList = highestWinsTeamList()

        usecase
            .execute(teamList, HIGHEST_WINS)
            .test()
            .assertResult(expectedTeamList)
    }

    @Test
    fun `test usecase sorts the teams according lowest losses`() {
        val teamList = teamList()
        val expectedTeamList = lowestLossesTeamList()

        usecase
            .execute(teamList, LOWEST_LOSSES)
            .test()
            .assertResult(expectedTeamList)
    }

    @Test
    fun `test usecase sorts the teams according highest losses`() {
        val teamList = teamList()
        val expectedTeamList = highestLossesTeamList()

        usecase
            .execute(teamList, HIGHEST_LOSSES)
            .test()
            .assertResult(expectedTeamList)
    }

    private fun teamList() = listOf(
        Team(teamName = "a", teamWins = 23, teamLosses = 54),
        Team(teamName = "d", teamWins = 43, teamLosses = 432),
        Team(teamName = "c", teamWins = 32, teamLosses = 65),
        Team(teamName = "b", teamWins = 54, teamLosses = 45)
    )

    private fun ascNameTeamList() = listOf(
        Team(teamName = "a", teamWins = 23, teamLosses = 54),
        Team(teamName = "b", teamWins = 54, teamLosses = 45),
        Team(teamName = "c", teamWins = 32, teamLosses = 65),
        Team(teamName = "d", teamWins = 43, teamLosses = 432)
    )

    private fun lowestWinsTeamList() = listOf(
        Team(teamName = "a", teamWins = 23, teamLosses = 54),
        Team(teamName = "c", teamWins = 32, teamLosses = 65),
        Team(teamName = "d", teamWins = 43, teamLosses = 432),
        Team(teamName = "b", teamWins = 54, teamLosses = 45)
    )

    private fun lowestLossesTeamList() = listOf(
        Team(teamName = "b", teamWins = 54, teamLosses = 45),
        Team(teamName = "a", teamWins = 23, teamLosses = 54),
        Team(teamName = "c", teamWins = 32, teamLosses = 65),
        Team(teamName = "d", teamWins = 43, teamLosses = 432)
    )

    private fun descNameTeamList() = ascNameTeamList().asReversed()
    private fun highestWinsTeamList() = lowestWinsTeamList().asReversed()
    private fun highestLossesTeamList() = lowestLossesTeamList().asReversed()
}