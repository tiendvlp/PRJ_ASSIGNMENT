package common;

import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dangminhtien
 */
public class ResouceDynamicMapping {

    public static final String KEY = "RESOUCE_DYNAMIC_MAPPING";
    HashMap<String, String> roadMap = new HashMap();
    String defaultUrl;

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }
    
    public ResouceDynamicMapping() {
    }

    public HashMap<String, String> getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(HashMap<String, String> roadMap) {
        this.roadMap = roadMap;
    }

}
