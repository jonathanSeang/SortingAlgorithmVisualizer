package Model;

import java.util.List;

import Controller.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import View.IObserver;

public class NumsModel implements IObservable{

	private int[] 		currSorting;
	private AllStates[] currStates;
	
	private List<IObserver> allObservers;
	
	//Possible states that an element can have
	private enum AllStates {
		STANDBY,
		VISITING,
		FINISHED
	}

	public NumsModel(int[] currSorting, Algorithms algorithm) {
		
		this.currSorting = currSorting;
		this.currStates = new AllStates[currSorting.length];
		Arrays.fill(currStates, AllStates.STANDBY);
		allObservers = new ArrayList<IObserver>();
		
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
	
	private void loopBubbleSort() {
		
		//testing is sorting works
		Arrays.sort(currSorting);
		alert();
		
	}
	
	private void loopMergeSort() {
		
	}
	
	private void loopHeapSort() {
		
	}
	
	private void loopQuickSort() {
		
	}
	
}
