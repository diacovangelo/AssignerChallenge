package com.test.challenge.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.test.challenge.Room;

/**
 * Utils class for list operations
 * 
 * @author diacovangelo
 *
 */
public class ListUtils {
	/**
	 * Returns a deep copy of a list of rooms - creating new rooms
	 * 
	 * @param rooms
	 * @return
	 */
	public static List<Room> deepCopyRooms(List<Room> rooms) {
		return rooms.stream().map(r -> r.clone()).collect(Collectors.toList());	
	}
	
	/**
	 * Creates a shallow copy of a list reusing the same elements
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> shallowCopy(List<T> list) {
		return new ArrayList<T>(list);
	}
}
