package freewilder.rockme.com.freewilder.pojo;

import org.json.JSONObject;

/**
 * Created by su on 6/8/17.
 */

public class SetGetProfileCategoryWiseData {
    int nthPosition;

    public int getNthPosition() {
        return nthPosition;
    }

    public void setNthPosition(int nthPosition) {
        this.nthPosition = nthPosition;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isFooter() {
        return isFooter;
    }

    public void setFooter(boolean footer) {
        isFooter = footer;
    }

    String categoryType;
    private boolean isHeader=false,isFooter=false;

    JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
