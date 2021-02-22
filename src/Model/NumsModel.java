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

	private void swap(int x, int y) {
		int temp = currSorting[x];
		currSorting[x] = currSorting[y];
		currSorting[y] = temp;
	}

	private void loopBubbleSort() {

		try {

			for (int i = 0; i < currSorting.length-1; i++) {

				//System.out.println("we're now on: " + currSorting[i]);

				for (int j = 0; j < currSorting.length-i-1; j++) {

					//System.out.println("Viewing: " + currSorting[j] + " and " + currSorting[j+1]);
					currStates[j] = AllStates.VISITING;
					currStates[j+1] = AllStates.VISITING;
					alert();

					Thread.sleep(speedTimer);

					if (currSorting[j] > currSorting[j+1]) {
						currStates[j] = AllStates.ALTERING;
						currStates[j+1] = AllStates.ALTERING;
						swap(j, j+1);	//maybe find a way to animate this in future
						alert();
						Thread.sleep(speedTimer);
					}

					currStates[j] = AllStates.STANDBY;
					currStates[j+1] = AllStates.STANDBY;
					alert();

				}
				
				//finalized last one
				currStates[currSorting.length-i-1] = AllStates.FINISHED;
				alert();
				
			}
			
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
