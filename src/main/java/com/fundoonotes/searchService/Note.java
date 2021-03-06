package com.fundoonotes.searchService;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author vikas gaikwad
 *
 */

/*
 * @JsonInclude(JsonInclude.Include.NON_EMPTY) :- Annotation used to indicate
 * when value of the annotated property (when used for a field, method or
 * constructor parameter), or all properties of the annotated class, is to be
 * serialized. Without annotation property values are always included, but by
 * using this annotation one can specify simple exclusion rules to reduce amount
 * of properties to write out.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Note {

	private String id;

	private String title;

	private String description;

	public Note() {

	}

	public Note(String id, String title, String description) {

		this.id = id;
		this.title = title;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

}
