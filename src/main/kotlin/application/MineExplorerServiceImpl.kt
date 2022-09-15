package application

import domain.Directions
import domain.MineMap
import domain.Miner
import domain.Position

class MineExplorerServiceImpl(var outputAdapter: OutputAdapter) : MineExplorerService {

    override fun solve(map: MutableList<MutableList<Boolean>>, minerPosition: Position, exit: Position) {
        var miner = Miner(minerPosition)
        var mineMap = MineMap(map)
        var lastMove = Directions.NULL
        var availablePathsAtPosition: List<Directions>
        var savedMinerPosition = Position(minerPosition.x, minerPosition.y)

        mineMap.isMinerInMap(miner.position)

        while (miner.position != exit) {

            availablePathsAtPosition = mineMap.exploreNextStep(lastMove, miner.position)
            lastMove = availablePathsAtPosition[0]

            mineMap.escapePath.add(lastMove)
            miner.move(lastMove)
            if (availablePathsAtPosition.size > 1)
                mineMap.lastUnsureStepInAFork = Position(miner.position.x, miner.position.y)


            if (endOfPath(lastMove)) {
                if (noForksDetected(mineMap))
                    throw Exception("HELP!! I'M TRAPPED")
                else {
                    mineMap.mapOrography[mineMap.lastUnsureStepInAFork!!.x][mineMap.lastUnsureStepInAFork!!.y] = false
                    miner.position = Position(savedMinerPosition.x,savedMinerPosition.y)
                    mineMap.escapePath = mutableListOf()

                }
            }
        }
        outputAdapter.printSolution(mineMap.escapePath)

    }

    private fun noForksDetected(mineMap: MineMap) = mineMap.lastUnsureStepInAFork == null


    private fun checkTrapped(lastMove: Directions) {
        if (endOfPath(lastMove))
            throw Exception("HELP!! I'M TRAPPED")
    }

    private fun endOfPath(lastMove: Directions) = lastMove == Directions.NULL


}