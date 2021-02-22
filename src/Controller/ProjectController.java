package Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import Controller.Message.ExecuteBubbleMessage;
import Controller.Message.IMessage;
import Model.NumsModel;
import View.BarsView;
import View.MenuView;
import View.NumbersView;

public class ProjectController {
	
	private MenuView menuView;
	private int[] intArray;
	
	private BlockingQueue<IMessage> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	
	public ProjectController(BlockingQueue<IMessage> queue) {
		
		this.queue = queue;
		menuView = new MenuView(queue);
		valves.add(new ExecuteBubbleMessageValve());
		//add more valves for each algorithm later
		
	}
	
	private void generateRandomArray() {
		intArray = new int[menuView.getSizeSlider()];
		
		for (int i = 0; i < menuView.getSizeSlider(); i++) {
			intArray[i] = (int)(Math.random()*(menuView.getSizeSlider()+1)+1);
		}
		
	}
	
	private interface Valve {
		public ValveResponse execute(IMessage message);
	}
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		IMessage message = null;
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
		public ValveResponse execute(IMessage message) {
			if (message.getClass() != ExecuteBubbleMessage.class) {
				return ValveResponse.MISS;
			}

			System.out.println("Beginning bubble sort");
			
			generateRandomArray();
			NumsModel numsModel = new NumsModel(intArray, menuView);
			NumbersView numbersView = new NumbersView(numsModel);
			BarsView barsView = new BarsView(numsModel);
			numsModel.sort(Algorithms.BUBBLE);
			
			return ValveResponse.EXECUTED;
		}
	}
	
	
}
