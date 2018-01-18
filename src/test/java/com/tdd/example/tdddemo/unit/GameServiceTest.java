package com.tdd.example.tdddemo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.google.common.collect.Lists;
import com.tdd.example.tdddemo.DateTimeService;
import com.tdd.example.tdddemo.GameService;
import com.tdd.example.tdddemo.entities.Match;
import com.tdd.example.tdddemo.entities.Player;
import com.tdd.example.tdddemo.entities.Team;
import com.tdd.example.tdddemo.repo.MatchRepo;

/**
 * Clase de tests unitarios para el servicio del juego
 * 
 * @author amaeztu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

	/**
	 * Repo falso de partidos
	 */
	@Mock
	private MatchRepo matchRepo;

	/**
	 * Servicio falso de fechas
	 */
	@Mock
	private DateTimeService dateTimeService;

	/**
	 * Capturador de argumentos de tipo partido
	 */
	@Captor
	private ArgumentCaptor<Match> matchCaptor;

	private GameService service;

	private Team developers;

	private Team industrial;

	@Before
	public void before() {
		service = new GameService(dateTimeService, matchRepo);
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
		when(matchRepo.save(any(Match.class))).thenAnswer(new Answer<Match>() {

			@Override
			public Match answer(InvocationOnMock invocation) throws Throwable {
				Match match = invocation.getArgumentAt(0, Match.class);
				match.setId(new Random().nextLong());
				return match;
			}
		});

		// Falsear servicio de fecha-hora
		LocalDateTime now = LocalDateTime.now();
		when(dateTimeService.now()).thenReturn(now);

		Match myMatch = service.createMatch(developers, industrial);
		assertEquals(5, myMatch.getLocal().getPlayers().size());
		assertEquals(5, myMatch.getVisitor().getPlayers().size());
		assertEquals(now, myMatch.getInitDate());
		verify(matchRepo, times(1)).save(matchCaptor.capture());
		assertEquals(developers, matchCaptor.getValue().getLocal());
		assertEquals(industrial, matchCaptor.getValue().getVisitor());

		// Comprobar asignación de id
		assertNotNull(myMatch.getId());
	}

	@Test
	public void testDBException() {
		when(matchRepo.save(any(Match.class)))
				.thenThrow(new RuntimeException("No se ha podido conectar a la base de datos!"));
		try {
			service.createMatch(developers, industrial);
			fail();
		} catch (RuntimeException e) {
		}
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
