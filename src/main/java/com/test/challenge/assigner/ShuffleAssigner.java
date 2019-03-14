package com.test.challenge.assigner;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import com.test.challenge.House;
import com.test.challenge.Person;
import com.test.challenge.Room;
import com.test.challenge.utils.ListUtils;

/**
 * ShuffleAssigner
 * 
 * @author diacovangelo
 *
 */
public class ShuffleAssigner implements Supplier<House>{
	
	private final List<Room> rooms;
	private final List<Person> persons;
	private final int iterations;
	
	/**
	 * Assigning persons to rooms with a shuffle algorithm
	 * 
	 * @param rooms
	 * @param persons
	 * @param iterations
	 */
	public ShuffleAssigner(List<Room> rooms, List<Person> persons, int iterations) {
		this.rooms = rooms;
		this.persons = persons;
		this.iterations = iterations;
	}

	@Override
	public House get() {
		return IntStream.range(0, iterations)
				.mapToObj(i -> calculate())
				.min(Comparator.comparing(House::calculateUnhappiness)).get();
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
