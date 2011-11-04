package war.hellotest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.CharacterSet;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class HelloJSON extends ServerResource {
	private static final String JSON_NAME_TABLE = "table";
	private static final String JSON_NAME_COLUMNS = "cols";
	private static final String JSON_NAME_COLUMNS_ID = "id";
	private static final String JSON_NAME_COLUMNS_LABEL = "label";
	private static final String JSON_NAME_COLUMNS_TYPE = "type";
	private static final String JSON_NAME_COLUMNS_PATTERN = "pattern";
	private static final String JSON_NAME_ROWS = "rows";
	private static final String JSON_NAME_ROWS_V = "v";
	private static final String JSON_NAME_ROWS_F = "f";
	
	
	/**
	 * test with: http://localhost:9090/hellotest/json
	 * @param variant
	 * @return
	 * @throws ResourceException
	 */
	@Get
	public Representation represent (Variant variant) throws ResourceException{
		JSONObject json = new JSONObject();
		try {
			json.put("requestId", "0");
			json.put("status", "ok");
			json.put("signature", "1234567890");
			json.put(this.JSON_NAME_TABLE, this.createTable());
		} catch (JSONException e){
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
		}
		JsonRepresentation jr = new JsonRepresentation(json);
		jr.setCharacterSet(CharacterSet.UTF_8);
		System.out.println("Hello JSON"+json.toString());
		return jr;
	}
	
	
	
	private JSONObject createTable() throws JSONException {
		JSONArray columns = new JSONArray();
		JSONArray rows = new JSONArray();
		JSONObject r_c = new JSONObject ();
		r_c.put(this.JSON_NAME_COLUMNS, columns);
		r_c.put(this.JSON_NAME_ROWS, rows);
		this.createColumns(columns);
	    this.createRows(rows);
	    return r_c;
	}
	
	private void createColumns(JSONArray columns) throws JSONException{
		columns.put(this.createColumn("A", "Date", "d", "M/d/yyyy"));
		columns.put(this.createColumn("B", "Budget", "n", "#0.###############"));
		columns.put(this.createColumn("C", "Revenue", "n", "#0.###############"));
		columns.put(this.createColumn("D", "Movie", "t", ""));
	}
	
	private void createRows(JSONArray rows) throws JSONException{
		JSONArray row = new JSONArray();
		row.put(this.createCell("new Date(1981,10,6)", "11/6/1981"));
		row.put(this.createCell("5000000.0", "5000000"));
		row.put(this.createCell("4.2365581E7", "42365581"));
		row.put(this.createCell("Time Bandits", null));
		rows.put(row);
	}
		  
	private JSONObject createCell(String v, String f) throws JSONException{
		JSONObject jo = new JSONObject();
		jo.put(JSON_NAME_ROWS_V, v);
		if(f != null)
			jo.put(JSON_NAME_ROWS_F, f);
		return jo;
	}
		  
	private JSONObject createColumn(String id, String label, String type, String pattern) throws JSONException{
		JSONObject jo = new JSONObject();
		jo.put(JSON_NAME_COLUMNS_ID, id);
		jo.put(JSON_NAME_COLUMNS_LABEL, label);
		jo.put(JSON_NAME_COLUMNS_TYPE, type);
		jo.put(JSON_NAME_COLUMNS_PATTERN, pattern);
		return jo;
	}

}
