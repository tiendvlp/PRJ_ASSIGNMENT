/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextlistener;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import common.ResouceDynamicMapping;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author dangminhtien
 */
public class MyApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            Gson gson = new Gson();
            File jsonFile = new File(context.getRealPath("/WEB-INF/roadmap.json"));
            JsonReader reader = new JsonReader(new FileReader(jsonFile));
            ResouceDynamicMapping resourceMapping = gson.fromJson(reader, ResouceDynamicMapping.class);
            context.setAttribute(ResouceDynamicMapping.KEY, resourceMapping);
        } catch (FileNotFoundException ex) {
            context.log("RoadMap file not found");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
