package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class TestClient {

	
	public static void main(String[] args) {
		GameEngine gameEngine=new GameEngineImpl();
		GameEngineCallbackGUI gameEngineCallbackGUI=new GameEngineCallbackGUI(gameEngine);
		gameEngine.addGameEngineCallback(gameEngineCallbackGUI);

	}

}
