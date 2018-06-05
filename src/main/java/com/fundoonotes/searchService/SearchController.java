/**
 * 
 */
package com.fundoonotes.searchService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vikas gaikwad
 *
 */
@RestController
@RequestMapping("/note")
public class SearchController {

	@Autowired
	ISearchService searchService;

	/* ======================================================================= */
	public SearchController(ISearchService searchService) {
		this.searchService = searchService;
	}

	/* ======================================================================= */
	@GetMapping("/get/{id}")
	public Map<String, Object> getNoteById(@PathVariable String id) {
		return searchService.getNoteById(id);
	}

	/* ======================================================================= */
	@PostMapping("/post")
	public Note insertNote(@RequestBody Note note) throws Exception {
		
		return searchService.insertNote(note);
	}
	/* ======================================================================= */

	@PutMapping("update/{id}")
	public Map<String, Object> updateNoteById(@RequestBody Note note, @PathVariable String id) {
		return searchService.updateNoteById(id, note);
	}

	/* ======================================================================= */
	@DeleteMapping("/delete/{id}")
	public void deleteNoteById(@PathVariable String id) {
		searchService.deleteNoteById(id);
	}

}
