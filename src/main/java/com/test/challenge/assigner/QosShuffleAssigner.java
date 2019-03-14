package com.test.challenge.assigner;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import com.test.challenge.House;
import com.test.challenge.Person;
import com.test.challenge.Room;
import com.test.challenge.utils.ListUtils;

/**
 * Quality of Service ShuffleAssigner
 * 
 * @author diacovangelo
 */
public class QosShuffleAssigner implements Supplier<House>{
	
	private final List<Room> rooms;
	private final List<Person> persons;
	private final long millisToRun;
	private final int desiredQuality;
	
	/**
	 * Assigning persons to rooms with a shuffle algorithm and QoS measures
	 * 
	 * @param rooms
	 * @param persons
	 * @param desiredQuality
	 * @param millisToRun
	 */
	public QosShuffleAssigner(List<Room> rooms, List<Person> persons, int desiredQuality, long millisToRun) {
		this.rooms = rooms;
		this.persons = persons;
		this.millisToRun = millisToRun;
		this.desiredQuality = desiredQuality;
	}

	@Override
	public House get() {
		long startTime = System.currentTimeMillis();
		House bestEfforResult = null;
		int counter = 0;
		
		while((System.currentTimeMillis() - startTime) < millisToRun) {
			House tmpHouse = calculate();
			counter++;
			
			if(tmpHouse.calculateUnhappiness() <= desiredQuality) {
				System.out.println("DesiredQuality found after " + (System.currentTimeMillis() - startTime) +
						"ms and " + counter + " iterations");
				return tmpHouse;
			}
			
			if(bestEfforResult == null || tmpHouse.calculateUnhappiness() < bestEfforResult.calculateUnhappiness()) {
				bestEfforResult = tmpHouse;
			}
		}
		System.out.println("DesiredQuality not found - returning best effort result after " + counter + " iterations");
		return bestEfforResult;
		
	}

	private House calculate() {
		// copy input before changing
		List<Person> pList = ListUtils.shallowCopy(persons);
		List<Room> rList = ListUtils.deepCopyRooms(rooms);
		
		// only shuffle persons
		Collections.shuffle(pList);
		
		int totalCapacity = rList.stream().mapToInt(r -> r.getCapacity()).sum();
		if (totalCapacity != pList.size()) {
			throw new IllegalStateException("Room capacity and number of persons does not match");
		}
		
		Iterator<Person> iter = pList.iterator();
		for(Room room : rList) {
			while(room.hasCapacity()) {
				room.addResident(iter.next());
			}
		}
		
		return new House(rList);
	}
}
