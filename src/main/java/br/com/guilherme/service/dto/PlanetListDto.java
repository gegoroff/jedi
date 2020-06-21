package br.com.guilherme.service.dto;

import java.util.List;

public class PlanetListDto {

	private Integer count;

	private List<PlanetDto> results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<PlanetDto> getResults() {
		return results;
	}

	public void setResults(List<PlanetDto> results) {
		this.results = results;
	}

}
