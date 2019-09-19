package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.render.controller.MainFrameController;


/**
 * The Main class.
 */
public class Main {

    public Main() {
        MainFrameController mainFrameController = new MainFrameController();
        mainFrameController.showMainFrameWindow();

        mainFrameController.createBoard();
    }

    public static void main(String[] args) {
        Main game = new Main();
    }

}
