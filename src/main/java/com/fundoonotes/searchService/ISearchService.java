/**
 * 
 */
package com.fundoonotes.searchService;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author vikas gaikwad
 *
 */
@Repository
public interface ISearchService {
	
	/* ======================================================================= */
	
	Map<String, Object> getNoteById(String id);

	/* ======================================================================= */
	
	Note insertNote(Note note);

	/* ======================================================================= */
	
	Map<String, Object> updateNoteById(String id, Note note);

	/* ======================================================================= */
	
	void deleteNoteById(String id);
	
	/* ======================================================================= */
}
