package br.com.guilherme.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("planet")
public class Planet {

	@PrimaryKey
	private Integer id;
	private String name;
	private String climate;
	private String terrain;
	@Column("filmappearances")
	private Integer filmAppearances;

	public Planet() {
	}

//
//	@JsonCreator
	public Planet(final Integer id, final String name, final String climate, final String terrain,
			final Integer filmAppearances) {
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.filmAppearances = filmAppearances;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getFilmAppearances() {
		return filmAppearances;
	}

	public void setFilmAppearances(Integer filmAppearances) {
		this.filmAppearances = filmAppearances;
	}

}
