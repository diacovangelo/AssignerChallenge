package com.test.challenge.assigner;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.test.challenge.House;
import com.test.challenge.Person;
import com.test.challenge.Room;
import com.test.challenge.assigner.ShuffleAssigner;
import com.test.challenge.assigner.SimpleAssigner;

/**
 * Unittest for different assigners
 * 
 * @author diacovangelo
 *
 */
public class AssignerTest {
	
	private List<Person> persons;
	private List<Room> rooms;

	@Before
	public void setUp() throws Exception {
		persons = Arrays.asList(
				new Person("JarJar"),
				new Person("Luke", "JarJar"),
				new Person("Vader", "Luke", "JarJar"),
				new Person("Lea", "Vader", "Luke", "JarJar"),
				new Person("C3PO", "JarJar"),
				new Person("R2D2", "C3PO", "JarJar"),
				new Person("Han", "C3PO", "JarJar"),
				new Person("Boba", "Han", "Luke", "JarJar"),
				new Person("Jabba", "Han", "Lea"));
		
		rooms = Arrays.asList(
				new Room(3),
				new Room(2),
				new Room(4));
	}

	@Test
	public void testShuffleAssigner() {
		House house = new ShuffleAssigner(rooms, persons, 1000).get();
		assertEquals(1, house.calculateUnhappiness());
	}

	@Test
	public void testConcurrentShuffleAssigner() {
		House house = new ConcurrentShuffleAssigner(rooms, persons, 1000).get();
		assertEquals(1, house.calculateUnhappiness());
	}

	@Test
	public void testAssignerRace() {
		long startShuffle = System.currentTimeMillis();
		new ShuffleAssigner(rooms, persons, 10000).get();
		long durationShuffle = System.currentTimeMillis() - startShuffle;
		System.out.println("ShuffleAssigner took: " + durationShuffle);
		
		long startConcurrent = System.currentTimeMillis();
		new ConcurrentShuffleAssigner(rooms, persons, 10000).get();
		long durationConcurrent = System.currentTimeMillis() - startConcurrent;
		System.out.println("ConcurrentAssigner took: " + durationConcurrent);
		
		assertTrue(durationConcurrent < durationShuffle);
	}

	@Test
	public void testQosShuffleAssigner() {
		House house = new QosShuffleAssigner(rooms, persons, 1, 3).get();
		assertTrue(house.calculateUnhappiness() <= 3);
	}
	
	@Test
	public void testSimpleAssigner() {
		House house = new SimpleAssigner(rooms, persons).get();
		assertTrue(house.calculateUnhappiness() < 7);
	}
	
	

}
