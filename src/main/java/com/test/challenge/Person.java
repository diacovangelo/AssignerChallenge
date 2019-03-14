package com.test.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Person class
 * 
 * @author diacovangelo
 *
 */
public class Person {
	private final String name;
	private final List<String> dislikes;
	
	/**
	 * Create a person without dislikes
	 * 
	 * @param name
	 */
	public Person(String name) {
		this.name = name;
		this.dislikes = Collections.emptyList();
	}
	
	/**
	 * Create a person with dislikes
	 * 
	 * @param name
	 * @param dislikes
	 */
	public Person(String name, String... dislikes) {
		super();
		this.name = name;
		this.dislikes = new ArrayList<>(dislikes.length);
		for(String dislike: dislikes) {
			this.dislikes.add(dislike);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getDislikes() {
		return dislikes;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", dislikes=" + dislikes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
