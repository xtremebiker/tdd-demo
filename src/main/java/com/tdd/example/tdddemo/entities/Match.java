package com.tdd.example.tdddemo.entities;

public class Match {

	private Team local;
	private Team visitor;
	private Score score = new Score(0, 0);

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

	public Score getScore() {
		return score;
	}

	public Match(Team local, Team visitor) {
		super();
		this.local = local;
		this.visitor = visitor;
	}

	public Team getLocal() {
		return local;
	}

	public Team getVisitor() {
		return visitor;
	}

}
