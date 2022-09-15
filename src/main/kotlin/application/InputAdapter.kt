package application

import domain.Position

interface InputAdapter {
    fun createMapOrography():List<List<Boolean>>

    fun createMiner():Position

    fun createExit():Position

}