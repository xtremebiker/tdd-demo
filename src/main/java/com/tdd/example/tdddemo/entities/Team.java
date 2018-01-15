package com.tdd.example.tdddemo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Team {

	private List<Player> players;

	public Team(Collection<Player> players) {
		this.players = new ArrayList<>(players);
	}

}
