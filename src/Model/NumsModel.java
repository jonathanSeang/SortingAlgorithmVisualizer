package Model;

import java.util.List;

import Controller.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import View.IObserver;

public class NumsModel implements IObservable{

	private int SLEEP_TIMER = 400;

	private int[] 		currSorting;
	private AllStates[] currStates;

	private List<IObserver> allObservers;

	//Possible states that an element can have
	private enum AllStates {
		STANDBY,
		VISITING,
		FINISHED
	}

	public NumsModel(int[] currSorting) {

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

	private void swap(int x, int y) {
		int temp = currSorting[x];
		currSorting[x] = currSorting[y];
		currSorting[y] = temp;
	}

	private void loopBubbleSort() {

		for (int i = 0; i < currSorting.length-1; i++) {

			System.out.println("we're now on: " + currSorting[i]);
			
			for (int j = 0; j < currSorting.length-i-1; j++) {

				System.out.println("Viewing: " + currSorting[j] + " and " + currSorting[j+1]);

				try {
					Thread.sleep(SLEEP_TIMER);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (currSorting[j] > currSorting[j+1]) {
					System.out.println("swapping: " + currSorting[j] + " and " + currSorting[j+1]);
					swap(j, j+1);
				}


				alert();

			}
		}

	}

	private void loopMergeSort() {

	}

	private void loopHeapSort() {

	}

	private void loopQuickSort() {

	}

}
