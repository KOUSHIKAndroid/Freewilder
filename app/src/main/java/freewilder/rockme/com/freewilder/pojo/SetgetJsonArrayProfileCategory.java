package freewilder.rockme.com.freewilder.pojo;

import org.json.JSONArray;

/**
 * Created by su on 6/8/17.
 */

public class SetgetJsonArrayProfileCategory {
    String header;
    JSONArray jsonArray;

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
