package domain

data class Miner(var position:Position) {

    fun move(direction:Directions){
        when (direction)
        {
            Directions.UP -> goUp()
            Directions.DOWN -> goDown()
            Directions.RIGHT -> goRight()
            Directions.LEFT -> goLeft()
            Directions.NULL -> goNull()
        }
    }
    private fun goUp(){
        position.y += 1
    }
    private fun goDown(){
        position.y -= 1
    }
    private fun goLeft(){
        position.x -= 1
    }
    private fun goRight(){
        position.x += 1
    }
    private fun goNull(){

    }


}