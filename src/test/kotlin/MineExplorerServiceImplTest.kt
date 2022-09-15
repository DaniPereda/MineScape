import application.MineExplorerServiceImpl
import application.OutputAdapter
import domain.Directions
import domain.MineMap
import domain.Miner
import infra.ConsoleOutputAdapter
import infra.HardcodeInputAdapter
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import domain.Position
import org.mockito.kotlin.verify
import javax.naming.SizeLimitExceededException
import kotlin.test.assertFailsWith

internal class MineExplorerServiceImplTest {
    private val mockConsoleOutputAdapter = mock<OutputAdapter>()




    @Test
    fun `all ok`(){
        //GIVEN
        var mineMap =listOf(
            listOf(true, false, true, true, true),
            listOf(true, true, true, false, true),
            listOf(false, false, false, true, true),
            listOf(false, false, false, true, false),
            listOf(false, false, true, true, false),
        )

        var resultToCheck = mutableListOf<Directions>(Directions.RIGHT, Directions.UP, Directions.UP, Directions.LEFT, Directions.UP, Directions.UP, Directions.RIGHT, Directions.RIGHT, Directions.DOWN)

        var minerPosition = Position(0,0)
        var exitPosition = Position(2,3)

        var sut = MineExplorerServiceImpl(mockConsoleOutputAdapter)
        //WHEN
        sut.solve(mineMap,minerPosition, exitPosition)

        //THEN
        verify(mockConsoleOutputAdapter).printSolution(resultToCheck)
    }

    @Test
    fun `exit not reachable`(){
        //GIVEN
        var mineMap =listOf(
            listOf(true, false, true, true, true),
            listOf(true, true, true, false, true),
            listOf(false, false, false, true, true),
            listOf(false, false, false, true, false),
            listOf(false, false, true, true, false),
        )

        var minerPosition = Position(0,0)
        var exitPosition = Position(4,0)

        var sut = MineExplorerServiceImpl(mockConsoleOutputAdapter)

        //WHEN

        //THEN
        assertFailsWith<Exception>(
            message = "HELP!! I'M TRAPPED",
            block = {
                sut.solve(mineMap,minerPosition, exitPosition)
            }
        )

    }

    @Test
    fun `path with forks - need con come back`()
    {
        //GIVEN
        var mineMap =listOf(
            listOf(true, false, true, true, true),
            listOf(true, true, true, false, true),
            listOf(true, false, false, true, true),
            listOf(true, true, false, true, false),
            listOf(true, false, false, true, false),
        )

        var resultToCheck = mutableListOf<Directions>(Directions.RIGHT, Directions.UP, Directions.UP, Directions.LEFT, Directions.UP, Directions.UP, Directions.RIGHT, Directions.RIGHT, Directions.DOWN)

        var minerPosition = Position(0,0)
        var exitPosition = Position(3,2)

        var sut = MineExplorerServiceImpl(mockConsoleOutputAdapter)
        //WHEN
        sut.solve(mineMap,minerPosition, exitPosition)

        //THEN
        verify(mockConsoleOutputAdapter).printSolution(resultToCheck)
    }


}