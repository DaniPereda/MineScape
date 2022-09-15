import application.MineExplorerService
import application.MineExplorerServiceImpl
import infra.ConsoleOutputAdapter
import infra.HardcodeInputAdapter

fun main(args: Array<String>) {


    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    val hardcodeInputAdapter = HardcodeInputAdapter()
    var mineExplorerService = MineExplorerServiceImpl(ConsoleOutputAdapter())

    mineExplorerService.solve(hardcodeInputAdapter.createMapOrography(), hardcodeInputAdapter.createMiner(), hardcodeInputAdapter.createExit())

}