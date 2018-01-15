package com.tdd.example.tdddemo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tdd.example.tdddemo.entities.Match;
import com.tdd.example.tdddemo.entities.Player;
import com.tdd.example.tdddemo.entities.Team;

@Service
public class GameService {

	private Logger logger = LoggerFactory.getLogger(GameService.class);

	/**
	 * Comprueba dorsales repetidos y si es caso lanza una excepción
	 * 
	 * @param players
	 */
	private void checkForRepeatedNumbers(Collection<Player> players) {
		Map<Integer, List<Player>> map = players.stream().collect(Collectors.groupingBy(Player::getNumber));
		if (map.values().stream().anyMatch(list -> list.size() > 1)) {
			throw new IllegalArgumentException("Dorsales repetidos!");
		}
	}

	/**
	 * Crea un partido con equipos local y visitante
	 * 
	 * @param local
	 * @param visitor
	 * @return
	 */
	public Match createMatch(Team local, Team visitor) {
		if (local.getPlayers().size() != 5 || visitor.getPlayers().size() != 5) {
			throw new IllegalArgumentException("Los equipos tienen que estar compuestos por cinco jugadores!");
		}
		checkForRepeatedNumbers(local.getPlayers());
		checkForRepeatedNumbers(visitor.getPlayers());
		logger.info("\nEQUIPO LOCAL:\n{}\nVISITANTE:\n{}", local, visitor);
		return new Match(local, visitor);
	}

}
