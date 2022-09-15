package infra

import application.InputAdapter
import domain.Position

class HardcodeInputAdapter:InputAdapter {
    override fun createMapOrography(): List<List<Boolean>> {
        return listOf(
            listOf(true, false, true, true, true),
            listOf(true, true, true, false, true),
            listOf(false, false, false, true, true),
            listOf(false, false, false, true, false),
            listOf(false, false, true, true, false),
        )
    }

    override fun createMiner(): Position {
        return Position(0,0)
    }

    override fun createExit(): Position {
        return Position(4,2)
    }

}