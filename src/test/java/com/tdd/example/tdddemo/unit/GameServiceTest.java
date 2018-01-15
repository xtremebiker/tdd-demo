package com.tdd.example.tdddemo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.tdd.example.tdddemo.GameService;
import com.tdd.example.tdddemo.entities.Match;
import com.tdd.example.tdddemo.entities.Player;
import com.tdd.example.tdddemo.entities.Team;

/**
 * Clase de tests unitarios para el servicio del juego
 * 
 * @author amaeztu
 *
 */
public class GameServiceTest {

	private GameService service = new GameService();

	private Team developers;

	private Team industrial;

	@Before
	public void before() {
		developers = new Team(Lists.newArrayList(new Player(1, "Javi"), new Player(2, "Irene"), new Player(3, "Aritz"),
				new Player(4, "Eneko"), new Player(5, "María")));
		industrial = new Team(Lists.newArrayList(new Player(1, "Arkaitz"), new Player(2, "Miguel Co"),
				new Player(3, "Aritz"), new Player(4, "Amaia"), new Player(5, "Naiara")));
	}

	/**
	 * Inicio correcto
	 */
	@Test
	public void testCorrectPlayerCount() {
		Match myMatch = service.createMatch(developers, industrial);
		assertEquals(5, myMatch.getLocal().getPlayers().size());
		assertEquals(5, myMatch.getVisitor().getPlayers().size());
	}

	/**
	 * Equipo con sólo cuatro jugadores
	 */
	@Test
	public void testIncorrectPlayerCount() {
		developers = new Team(Lists.newArrayList(new Player(1, "Javi"), new Player(2, "Irene"), new Player(3, "Aritz"),
				new Player(4, "Eneko")));
		try {
			service.createMatch(developers, industrial);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Dorsal 4 repetido
	 */
	@Test
	public void testIncorrectNumber() {
		developers = new Team(Lists.newArrayList(new Player(4, "Javi"), new Player(2, "Irene"), new Player(3, "Aritz"),
				new Player(4, "Eneko"), new Player(5, "María")));
		try {
			service.createMatch(developers, industrial);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Comprueba el marcador 0-0
	 */
	@Test
	public void testScore() {
		Match match = service.createMatch(developers, industrial);
		assertEquals(new Match.Score(0, 0), match.getScore());
	}

	/**
	 * 
	 */
	@Test
	public void testTeamToString() {
		System.out.println("Testeando toString()...");
		System.out.println(developers);
		System.out.println(
				new Team(Lists.newArrayList(new Player(1, "Javi"), new Player(2, "Irene"), new Player(3, "Aritz"))));
	}

}
