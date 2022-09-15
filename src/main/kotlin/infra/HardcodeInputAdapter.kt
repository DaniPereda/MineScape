package infra

import application.InputAdapter
import domain.Position

class HardcodeInputAdapter:InputAdapter {
    override fun createMapOrography(): MutableList<MutableList<Boolean>> {
        return mutableListOf(
            mutableListOf(true, true, true, true, true),
            mutableListOf(false, false, true, false, false),
            mutableListOf(true, true, true, true, true),
            mutableListOf(false, false, true, false, false),
            mutableListOf(true, true, true, true, true),
        )
    }

    override fun createMiner(): Position {
        return Position(1,0)
    }

    override fun createExit(): Position {
        return Position(4,0)
    }

}