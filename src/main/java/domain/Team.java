package domain;

public enum Team {

	TEAM_1("Team 1"),
	TEAM_2("Team 2");

	private String name;

	private Team(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Team getTeam(final String name) {
		for (Team team : values()) {
			if (team.name.equals(name)) {
				return team;
			}
		}
		return null;
	}
}
