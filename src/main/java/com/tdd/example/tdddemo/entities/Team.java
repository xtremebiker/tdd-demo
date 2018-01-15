package com.tdd.example.tdddemo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Team {

	private List<Player> players;

	public List<Player> getPlayers() {
		return ImmutableList.copyOf(players);
	}

	public Team(Collection<Player> players) {
		this.players = new ArrayList<>(players);
	}

	@Override
	public String toString() {
		String result = "        " + players.get(0) + "\n------------------------------\n" + players.get(1) + "        " + players.get(2);
		if (players.size() > 3) {
			result = result.concat("        " + players.get(3));
		}
		if (players.size() > 4) {
			result = result.concat("\n------------------------------\n     " + players.get(4));
		}
		return result;
	}

}
