package application

import domain.Directions
import domain.MineMap
import domain.Position

interface MineExplorerService {
    fun solve (map: List<List<Boolean>>, minerPosition: Position, exit: Position)

}