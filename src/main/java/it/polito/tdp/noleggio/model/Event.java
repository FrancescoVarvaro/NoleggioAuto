package it.polito.tdp.noleggio.model;

import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
//	'enum' --> SONO CLASSI CHE DEFINISCONO UNA SERIE DI COSTANTI !
	public enum EventType {
		NUOVO_CLIENTE,  
		AUTO_RESTITUITA
	}
	
	private LocalTime time ;
	private EventType type ;
	
	
	public Event(LocalTime time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Event other = (Event) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time); //verranno prima gli eventi con un tempo inferiore !! SEMPRE COSI' !!
	}
	
	
	

}
