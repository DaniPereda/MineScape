package application

import domain.Directions
import domain.MineMap
import domain.Miner
import domain.Position

class MineExplorerServiceImpl(var outputAdapter: OutputAdapter) : MineExplorerService {

     override fun solve(map: List<List<Boolean>>, minerPosition: Position, exit: Position) {
        var miner = Miner(minerPosition)
        var mineMap = MineMap(map)
        var lastMove = Directions.NULL

        mineMap.isMinerInMap(miner.position)

        while (miner.position != exit) {
            lastMove = mineMap.exploreNextStep(lastMove, miner.position)
            mineMap.escapePath.add(lastMove)
            miner.move(lastMove)

            checkTrapped(lastMove)
        }
        outputAdapter.printSolution(mineMap.escapePath)

    }



    private fun checkTrapped(lastMove: Directions) {
        if (lastMove == Directions.NULL)
            throw Exception("HELP!! I'M TRAPPED")
    }


}