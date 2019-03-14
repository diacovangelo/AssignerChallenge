package com.test.challenge.assigner;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import com.test.challenge.House;
import com.test.challenge.Person;
import com.test.challenge.Room;

/**
 * SimpleAssigner
 * 
 * @author diacovangelo
 *
 */
public class SimpleAssigner implements Supplier<House>{
	
	private final List<Room> rooms;
	private final List<Person> persons;
	
	/**
	 * Assigning persons to rooms with a concurrent simple loop assignment
	 * 
	 * @param rooms
	 * @param persons
	 */
	public SimpleAssigner(List<Room> rooms, List<Person> persons) {
		this.rooms = rooms;
		this.persons = persons;
	}

	@Override
	public House get() {
		int totalCapacity = rooms.stream().mapToInt(r -> r.getCapacity()).sum();
		if (totalCapacity != persons.size()) {
			throw new IllegalStateException("Room capacity and number of persons does not match");
		}
		
		Iterator<Person> iter = persons.iterator();
		for(Room room : rooms) {
			while(room.hasCapacity()) {
				room.addResident(iter.next());
			}
		}
		
		return new House(rooms);
	}
	
	
}
