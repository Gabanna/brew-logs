package de.rgse.brewlog.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.rgse.brewlog.validation.Between;

@Entity
@SuppressWarnings("serial")
public class Evaluation extends BaseDomain<Long> {

	private String comment;

	@Between(from = 0, to = 10)
	private Integer rating;

	@Enumerated(EnumType.STRING)
	private EvaluationType type;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public EvaluationType getType() {
		return type;
	}

	public void setType(EvaluationType type) {
		this.type = type;
	}

	public enum EvaluationType {
		TASTE, COLOUR, CARBONATION, FROTH;
	}
}
