package domain

import org.junit.jupiter.api.Test
import javax.naming.SizeLimitExceededException
import kotlin.test.assertFailsWith

class MineMapTest {
    @Test
    fun `too much columns`(){
        var testMineMap =mutableListOf(
            mutableListOf(true, false, true, true, true, true),
            mutableListOf(true, false, true, true, true, true),
            mutableListOf(true, true, true, false, true, true),
            mutableListOf(false, false, false, true, true, true),
            mutableListOf(false, false, false, true, false, true))

        assertFailsWith<SizeLimitExceededException>(
            message = "Map too big",
            block = {
                MineMap(testMineMap)
            }
        )
    }

    @Test
    fun `too much rows`(){
        var testMineMap =mutableListOf(
            mutableListOf(true, false, true, true, true),
            mutableListOf(true, false, true, true, true),
            mutableListOf(true, true, true, false, true),
            mutableListOf(false, false, false, true, true),
            mutableListOf(false, false, false, true, false),
            mutableListOf(false, false, false, true, false)
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
        var testMineMap = mutableListOf<MutableList<Boolean>>()

        assertFailsWith<SizeLimitExceededException>(
            message = "Map empty",
            block = {
                MineMap(testMineMap)
            }
        )
    }

    @Test
    fun `miner out of Map exceeded cols`(){
        var testMineMap =mutableListOf(
            mutableListOf(true, false),
            mutableListOf(true, false)
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
        var testMineMap =mutableListOf(
            mutableListOf(true, false),
            mutableListOf(true, false)
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
        var testMineMap =mutableListOf(
            mutableListOf(true, false),
            mutableListOf(true, false)
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
        var testMineMap =mutableListOf(
            mutableListOf(true, false),
            mutableListOf(true, false)
        )

        assertFailsWith<SizeLimitExceededException>(
            message = "Miner Out Of Map",
            block = {
                MineMap(testMineMap).isMinerInMap(Position(1,-1))
            }
        )
    }

    @Test
    fun `Miner placed out of the path`(){
        var testMineMap =mutableListOf(
            mutableListOf(true, false),
            mutableListOf(true, false)
        )

        assertFailsWith<Exception>(
            message = "Miner placed out of the path",
            block = {
                MineMap(testMineMap).isMinerPlacedInCorrectSquare(Position(0,1))
            }
        )
    }
}