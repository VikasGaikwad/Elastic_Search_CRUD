package com.fundoonotes.searchService;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class SearchDaoImpl implements ISearchDao {

	private final String INDEX = "notedata";
	private final String TYPE = "notes";

	@Autowired
	private RestHighLevelClient restHighLevelClient;
	/**
	 * ObjectMapper :-ObjectMapper provides functionality for reading and writing
	 * JSON, either to and from basic POJOs (Plain Old Java Objects), or to and from
	 * a general-purpose JSON Tree Model (JsonNode), as well as related
	 * functionality for performing conversions. It is also highly customizable to
	 * work both with different styles of JSON content, and to support more advanced
	 * Object concepts such as polymorphism and Object identity.
	 */
	private ObjectMapper objectMapper = new ObjectMapper();
	/* ======================================================================= */

	public SearchDaoImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	/* ======================================================================= */
	public SearchDaoImpl() {

	}
	/* ======================================================================= */

	/*
	 * GetRequest :- A request to get a document (its source) from an index based on
	 * its type (optional) and id. Best created using
	 * org.elasticsearch.client.Requests.getRequest(String).The operation requires
	 * the index(), type(String) and id(String) to be set.
	 */

	/*
	 * GetResponse :- The response of a get action.
	 */
	@Override
	public Map<String, Object> get(String id) {
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		return sourceAsMap;
	}
	/* ======================================================================= */

	/*
	 * convertValue() :- Convenience method for doing two-step conversion from given
	 * value, into instance of given value type, if (but only if!) conversion is
	 * needed. If given value is already of requested type, value is returned as is.
	 */
	@Override
	public Note save(Note note) {
		// System.out.println("************"+note.getTitle());
		Map dataMap = objectMapper.convertValue(note, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, note.getId()).source(dataMap);
		try {
			IndexResponse response = restHighLevelClient.index(indexRequest);
		} catch (ElasticsearchException e) {
			e.getDetailedMessage();
		} catch (java.io.IOException ex) {
			ex.getLocalizedMessage();
		}
		return note;

	}
	/* ======================================================================= */

	@Override
	public Map<String, Object> update(String id, Note note) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true); // Fetch Object after its
																							// update
		Map<String, Object> error = new HashMap<>();
		error.put("Error", "Unable to update book");
		try {
			String noteJson = objectMapper.writeValueAsString(note);
			updateRequest.doc(noteJson, XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
			Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
			return sourceAsMap;
		} catch (JsonProcessingException e) {
			e.getMessage();
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		return error;
	}
	/* ======================================================================= */

	/*
	 * DeleteRequest :-A request to delete a document from an index based on its
	 * type and id. Best created using
	 * org.elasticsearch.client.Requests.deleteRequest(String).
	 * 
	 */
	@Override
	public void delete(String id) {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);

		try {
			DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
	}
	/* ======================================================================= */
}
