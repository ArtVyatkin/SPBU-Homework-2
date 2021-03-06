package homeworks.homework7.task2.views

import homeworks.homework7.task2.controllers.GameFieldController
import homeworks.homework7.task2.styles.GameFieldStyle
import tornadofx.View
import tornadofx.vbox
import tornadofx.label
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.button
import tornadofx.action
import tornadofx.plusAssign

class GameField : View() {
    private val controller: GameFieldController by inject()

    override fun onDock() {
        controller.newGameHandling()
        super.onDock()
    }

    override val root = vbox {
        addClass(GameFieldStyle.mainVbox)

        label(controller.gameStatus) { addClass(GameFieldStyle.statusBar) }

        vbox {
            addClass(GameFieldStyle.column)

            for (i in 0..2) {
                this += hbox { addClass(GameFieldStyle.row) }
            }

            for (i in 0..2) {
                for (j in 0..2) {
                    this.children[i] += button {
                        addClass(GameFieldStyle.gameButton)
                        id = "$i-$j"
                        action { controller.humanMoveHandling(id) }
                    }
                }
            }
        }

        button("Back to menu") {
            addClass(GameFieldStyle.menuButton)

            action {
                replaceWith<Menu>()
            }
        }
    }
}
