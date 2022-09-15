package domain

import javax.naming.SizeLimitExceededException

data class MineMap(var mapOrography: List<List<Boolean>>) {

    private val height:Int
    private val width:Int
    init {
        if ((mapOrography.isEmpty()) or (mapOrography[0].isEmpty()))
            throw SizeLimitExceededException("Map empty")
        else {
            height = mapOrography[0].size
            width = mapOrography.size
            if (widthExceeded() or heightExceeded())
                throw SizeLimitExceededException("Map too big")
        }


    }

    var escapePath = mutableListOf<Directions>()

    fun isMinerInMap(position: Position): Boolean {
        if (!isInMap(position))
            throw SizeLimitExceededException("Miner Out Of Map")
        return true
    }

    fun exploreNextStep(lastMovement: Directions, position: Position): Directions {
        var newPositionToExploreUp = position.up()
        var newPositionToExploreDown = position.down()
        var newPositionToExploreRight = position.right()
        var newPositionToExploreLeft = position.left()


        return if (notTurnDown(lastMovement) and positionFree(newPositionToExploreDown))
            Directions.DOWN
        else if (notTurnUp(lastMovement) and positionFree(newPositionToExploreUp))
            Directions.UP
        else if (notTurnRight(lastMovement) and positionFree(newPositionToExploreRight))
            Directions.RIGHT
        else if (notTurnLeft(lastMovement) and positionFree(newPositionToExploreLeft))
            Directions.LEFT
        else
            Directions.NULL


    }

    private fun notTurnRight(lastMovement: Directions) = (lastMovement != Directions.LEFT)

    private fun notTurnLeft(lastMovement: Directions) = (lastMovement != Directions.RIGHT)

    private fun notTurnUp(lastMovement: Directions) = (lastMovement != Directions.DOWN)

    private fun notTurnDown(lastMovement: Directions) = (lastMovement != Directions.UP)

    private fun positionFree(position: Position): Boolean {
        return if (isInMap(position))
            mapOrography[position.x][position.y]
        else
            false
    }

    private fun isInMap(position: Position) = !(negativePosition(position) or positionExceeded(position))

    private fun positionExceeded(position: Position) = (xExceeded(position) or yExceeded(position))

    private fun yExceeded(position: Position) = (position.y >= height)

    private fun xExceeded(position: Position) = (position.x >= width)

    private fun negativePosition(position: Position) = (position.x < 0) or (position.y < 0)


    private fun heightExceeded() = (height > 5)

    private fun widthExceeded() = (width > 5)


}