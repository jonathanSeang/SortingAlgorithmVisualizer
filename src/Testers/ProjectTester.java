package Testers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.ProjectController;
import Controller.Message.IMessage;

public class ProjectTester {

    private static BlockingQueue<IMessage> queue = new LinkedBlockingQueue<>();
	
	public static void main(String[] args) {
		
		ProjectController projectController = new ProjectController(queue);
		projectController.mainLoop();
		
	}
	
}
