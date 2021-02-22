package Model;

import java.util.List;

import Controller.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import View.IObserver;
import View.MenuView;

public class NumsModel implements IObservable{

	private int[] 		currSorting;
	private AllStates[] currStates;

	private MenuView menuView;	//need data on speed 
	private int speedTimer;
	private List<IObserver> allObservers;

	public NumsModel(int[] currSorting, MenuView menuView) {

		this.menuView = menuView;
		this.speedTimer = menuView.getSpeedSlider()*10;
		this.currSorting = currSorting;
		this.currStates = new AllStates[currSorting.length];
		Arrays.fill(currStates, AllStates.STANDBY);
		allObservers = new ArrayList<IObserver>();

	}

	public void sort(Algorithms algorithm) {

		switch(algorithm) {

		case BUBBLE:
			loopBubbleSort();
			break;

		case MERGE:
			loopMergeSort();
			break;

		case HEAP:
			loopHeapSort();
			break;

		case QUICK:
			loopQuickSort();
			break;

		}
	}

	@Override
	public void add(IObserver oberver) {
		this.allObservers.add(oberver);	
	}

	@Override
	public void alert() {

		for (IObserver observer: allObservers) {
			observer.update();
		}

	}

	public int[] getCurrSorting() {
		return currSorting;
	}	
	
	public AllStates[] getCurrStates() {
		return currStates;
	}

	
	private void changeStates(int x, AllStates newState) {
		currStates[x] = newState;
		alert();
	}
	
	private void changeStates(int x, int y, AllStates newState) {
		currStates[x] = newState;
		currStates[y] = newState;
		alert();
	}
	
	
	
	private void swap(int x, int y) {
		
		changeStates(x, y, AllStates.ALTERING);
		
		int temp = currSorting[x];
		currSorting[x] = currSorting[y];
		currSorting[y] = temp;
		alert();
		
		try {
			Thread.sleep(speedTimer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void loopBubbleSort() {

		try {

			for (int i = 0; i < currSorting.length-1; i++) {

				for (int j = 0; j < currSorting.length-i-1; j++) {

					changeStates(j, j+1, AllStates.VISITING);

					Thread.sleep(speedTimer);

					if (currSorting[j] > currSorting[j+1]) {
						swap(j, j+1);	//maybe find a way to animate this in future
					}
					changeStates(j, j+1, AllStates.STANDBY);
				}
				
				//finalized last ones
				changeStates(currSorting.length-i-1, AllStates.FINISHED);
				
			}
			
			changeStates(0, AllStates.FINISHED);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void loopMergeSort() {

	}

	private void loopHeapSort() {

	}

	private void loopQuickSort() {

	}

}
