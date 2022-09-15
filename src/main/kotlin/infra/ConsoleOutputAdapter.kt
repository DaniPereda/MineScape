package infra

import application.OutputAdapter
import domain.Directions

class ConsoleOutputAdapter:OutputAdapter {
    override fun printSolution(solved:MutableList<Directions>)
    {
        printPathInConsole(translateSolution(solved))
    }

    private fun translateSolution(solved:MutableList<Directions>):List<String>
    {
        var result:MutableList<String> = mutableListOf()
        for(direction in solved)
        {
            result.add(translateDirection(direction))
        }
        return result.toList()
    }

    private fun translateDirection(direction: Directions) = when (direction) {
        Directions.NULL -> ""
        Directions.UP -> "up"
        Directions.DOWN -> "down"
        Directions.LEFT -> "left"
        Directions.RIGHT -> "right"

    }

    private fun printPathInConsole(listToPrint:List<String>)
    {

        println("We did it!!! The miner is safe!!")
        for(dir in listToPrint)
            println(" $dir -")
        println(" EXIT ")
    }
}