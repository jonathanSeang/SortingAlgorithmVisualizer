package Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import Controller.Message.ExecuteBubbleMessage;
import Controller.Message.Message;
import Model.NumsModel;
import View.MenuView;
import View.NumbersView;

public class ProjectController {
	
	private MenuView menuView;
	private BlockingQueue<Message> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	
	public ProjectController(BlockingQueue<Message> queue) {
		
		this.queue = queue;
		menuView = new MenuView(queue);
		valves.add(new ExecuteBubbleMessageValve());
		//add more valves for each algorithm later
		
	}
	
	private interface Valve {
		public ValveResponse execute(Message message);
	}
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
				message = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (Valve valve : valves) {
				response = valve.execute(message);
				if (response != ValveResponse.MISS) {
					break;
				}
			}
		}
	}
	
	private class ExecuteBubbleMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != ExecuteBubbleMessage.class) {
				return ValveResponse.MISS;
			}

			System.out.println("pressed bubble");
			
			int[] arrOfElements = {10, 3, 5 ,7, 2, 8};	//generate random array later
			NumsModel numsModel = new NumsModel(arrOfElements, Algorithms.BUBBLE);
			NumbersView numbersView = new NumbersView(numsModel);
			
			return ValveResponse.EXECUTED;
		}
	}
	
	
}
