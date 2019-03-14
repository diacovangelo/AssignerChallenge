package com.test.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Room class
 * 
 * @author diacovangelo
 *
 */
public class Room {
	private final List<Person> residents;
	private final int capacity;
	
	/**
	 * Create a new Room with given capacity
	 * @param capacity
	 */
	public Room(int capacity) {
		super();
		this.capacity = capacity;
		this.residents = new ArrayList<>(capacity);
	}

	/**
	 * Assigne a Person to the room
	 * @param p
	 */
	public void addResident(Person p) {
		if(!hasCapacity()) {
			throw new IllegalStateException("Room does not have any more capacity");
		}
		residents.add(p);
	}
	
	/**
	 * Check if the room has free capacity
	 * 
	 * @return
	 */
	public boolean hasCapacity() {
		return capacity > residents.size();
	}
	
	/**
	 * Calculate the combined unhappiness of all persons in the room
	 * 
	 * @return
	 */
	public int calculateUnhappiness() {
		int result = 0;
		
		for(Person resident : residents) {
			for(String dislike : resident.getDislikes()) {
				if (residents.contains(new Person(dislike))) {
					result++;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room (" + calculateUnhappiness() + ")\n");
		for(Person p : residents) {
			sb.append("- " + p.getName() + "\n");
		}
		return sb.toString();
	}

	public int getCapacity() {
		return capacity;
	}
	
	public Room clone() {
		return new Room(capacity);
	}
	
}
