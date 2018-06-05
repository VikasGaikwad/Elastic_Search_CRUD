package com.fundoonotes.searchService;

import java.util.Map;

/**
 * @author vikas gaikwad
 *
 */
public interface ISearchDao {

	/* ======================================================================= */

	Map<String, Object> get(String id);
	
	/* ======================================================================= */

	Note save(Note note);
	
	/* ======================================================================= */

	Map<String, Object> update(String id, Note note);
	
	/* ======================================================================= */

	void delete(String id);
	
	/* ======================================================================= */

}
