/**
 * 
 */
package com.fundoonotes.searchService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vikas gaikwad
 *
 */
@Service
public class SearchServiceImpl implements ISearchService {

	@Autowired
	ISearchDao searchDao;
	/* ======================================================================= */

	@Override
	public Map<String, Object> getNoteById(String id) {
		
		return searchDao.get(id);
	}
	/* ======================================================================= */

	@Override
	public Note insertNote(Note note) {
		searchDao.save(note);
		return null;
	}
	/* ======================================================================= */

	@Override
	public Map<String, Object> updateNoteById(String id, Note note) {
		searchDao.update(id, note);
		return null;
	}
	/* ======================================================================= */

	@Override
	public void deleteNoteById(String id) {
		searchDao.delete(id);
	}
	/* ======================================================================= */

}
