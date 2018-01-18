package com.tdd.example.tdddemo.entities;

import java.time.LocalDateTime;

public class Match {

	public static class Score {
		private int local = 0;
		private int visitor = 0;

		public Score(int local, int visitor) {
			super();
			this.local = local;
			this.visitor = visitor;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Score other = (Score) obj;
			if (local != other.local)
				return false;
			if (visitor != other.visitor)
				return false;
			return true;
		}

	}

	private Team local;
	private Team visitor;
	private Score score = new Score(0, 0);

	private LocalDateTime initDate;

	private Long id;

	public Match(Team local, Team visitor, LocalDateTime initDate) {
		super();
		this.local = local;
		this.visitor = visitor;
		this.initDate = initDate;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInitDate() {
		return initDate;
	}

	public Team getLocal() {
		return local;
	}

	public Score getScore() {
		return score;
	}

	public Team getVisitor() {
		return visitor;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
