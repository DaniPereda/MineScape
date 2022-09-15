package domain

import org.junit.jupiter.api.Test
import javax.naming.SizeLimitExceededException
import kotlin.test.assertFailsWith

class MineMapTest {
    @Test
    fun `too much columns`(){
        var testMineMap =listOf(
            listOf(true, false, true, true, true, true),
            listOf(true, false, true, true, true, true),
            listOf(true, true, true, false, true, true),
            listOf(false, false, false, true, true, true),
            listOf(false, false, false, true, false, true))

        assertFailsWith<SizeLimitExceededException>(
            message = "Map too big",
            block = {
                MineMap(testMineMap)
            }
        )
    }

    @Test
    fun `too much rows`(){
        var testMineMap =listOf(
            listOf(true, false, true, true, true),
            listOf(true, false, true, true, true),
            listOf(true, true, true, false, true),
            listOf(false, false, false, true, true),
            listOf(false, false, false, true, false),
            listOf(false, false, false, true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Map too big",
            block = {
                MineMap(testMineMap)
            }
        )
    }

    @Test
    fun `map empty`(){
        var testMineMap = emptyList<List<Boolean>>()

        assertFailsWith<SizeLimitExceededException>(
            message = "Map empty",
            block = {
                MineMap(testMineMap)
            }
        )
    }

    @Test
    fun `miner out of Map exceeded cols`(){
        var testMineMap =listOf(
            listOf(true, false),
            listOf(true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Miner Out Of Map",
            block = {
                MineMap(testMineMap).isMinerInMap(Position(1,2))
            }
        )
    }

    @Test
    fun `miner out of Map exceeded rows`(){
        var testMineMap =listOf(
            listOf(true, false),
            listOf(true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Miner Out Of Map",
            block = {
                MineMap(testMineMap).isMinerInMap(Position(2,1))
            }
        )
    }

    @Test
    fun `miner out of Map negative row position`(){
        var testMineMap =listOf(
            listOf(true, false),
            listOf(true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Miner Out Of Map",
            block = {
                MineMap(testMineMap).isMinerInMap(Position(-1,1))
            }
        )
    }

    @Test
    fun `miner out of Map negative columns position`(){
        var testMineMap =listOf(
            listOf(true, false),
            listOf(true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Miner Out Of Map",
            block = {
                MineMap(testMineMap).isMinerInMap(Position(1,-1))
            }
        )
    }
}