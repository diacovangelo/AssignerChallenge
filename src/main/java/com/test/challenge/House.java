package com.test.challenge;

import java.util.List;

/**
 * House class
 * 
 * @author diacovangelo
 *
 */
public class House {
	private final List<Room> rooms;
	
	/**
	 * Create a house based on a list of rooms
	 * 
	 * @param rooms
	 */
	public House(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	/**
	 * Calculate combined Unhappiness of all rooms
	 * 
	 * @return
	 */
	public int calculateUnhappiness() {
		return rooms.stream()
				.mapToInt(r -> r.calculateUnhappiness())
				.sum();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("House (" + calculateUnhappiness() + ")\n");
		for(Room r : rooms) {
			sb.append(r.toString());
		}
		return sb.toString();
	}
}
