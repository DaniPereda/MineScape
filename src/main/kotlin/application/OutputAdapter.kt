package application

import domain.Directions

interface OutputAdapter {
    fun printSolution(solved:MutableList<Directions>)
}