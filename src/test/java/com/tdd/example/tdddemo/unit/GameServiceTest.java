package com.tdd.example.tdddemo.unit;

import com.google.common.collect.Lists;
import com.tdd.example.tdddemo.entities.Player;
import com.tdd.example.tdddemo.entities.Team;

public class GameServiceTest {

	private Team developers = new Team(Lists.newArrayList(new Player(1, "Javi"), new Player(2, "Irene"),
			new Player(3, "Aritz"), new Player(4, "Eneko"), new Player(5, "María")));

	private Team industrial = new Team(Lists.newArrayList(new Player(1, "Arkaitz"), new Player(2, "Miguel Co"),
			new Player(3, "Aritz"), new Player(4, "Amaia"), new Player(5, "Naiara")));

}
