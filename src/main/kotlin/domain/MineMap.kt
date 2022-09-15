package domain

import javax.naming.SizeLimitExceededException

data class MineMap(var mapOrography: MutableList<MutableList<Boolean>>) {
    private val height: Int
    private val width: Int
    var lastUnsureStepInAFork: Position? = null

    init {
        if (mapOrography.isEmpty()) {
            throw SizeLimitExceededException("Map empty")
        } else if (mapOrography[0].isEmpty())
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
    fun isMinerPlacedInCorrectSquare(position: Position): Boolean {
        if (!isInMap(position))
            throw SizeLimitExceededException("Miner Out Of Map")
        if(minerPlacedAtIncorrectSquare(position))
            throw Exception("Miner placed out of the path")
        return true
    }

    private fun minerPlacedAtIncorrectSquare(position: Position) = !mapOrography[position.x][position.y]

    fun exploreNextStep(lastMovement: Directions, position: Position): List<Directions> {

        var directionsAvailableAtPosition = addAvailableDirections(lastMovement, position)

        notPathAvailableReturnNULLDirection(directionsAvailableAtPosition)

        return directionsAvailableAtPosition


    }

    private fun addAvailableDirections(
        lastMovement: Directions,
        position: Position
    ):MutableList<Directions> {
        var directionsAvailableAtPosition: MutableList<Directions> = mutableListOf()
        if (notTurnDown(lastMovement) and positionFree(position.down()))
            directionsAvailableAtPosition.add(Directions.DOWN)
        if (notTurnUp(lastMovement) and positionFree(position.up()))
            directionsAvailableAtPosition.add(Directions.UP)
        if (notTurnRight(lastMovement) and positionFree(position.right()))
            directionsAvailableAtPosition.add(Directions.RIGHT)
        if (notTurnLeft(lastMovement) and positionFree(position.left()))
            directionsAvailableAtPosition.add(Directions.LEFT)
        return directionsAvailableAtPosition
    }

    private fun notPathAvailableReturnNULLDirection(directionsAvailableAtPosition: MutableList<Directions>) {
        if (directionsAvailableAtPosition.size == 0)
            directionsAvailableAtPosition.add(Directions.NULL)
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