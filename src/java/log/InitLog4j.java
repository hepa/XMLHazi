package log;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class InitLog4j extends HttpServlet {
    
    private Logger logger;
    
    @Override
    public void init() {
        String initPath = getInitParameter("logPath");
        String logPath = "/WEB-INF/logs/error.log";
        if (initPath != null) {
            logPath = initPath;
        }
        FileAppender appender = getAppender(logPath);
        if (appender == null) {
            return;
        }
        initLogger(null, appender, Level.DEBUG);
    }

    private FileAppender getAppender(String fileName) {
        RollingFileAppender appender = null;
        try {
            //appender = new RollingFileAppender(new PatternLayout("[%-5p] %d - %m%n   Logger's name: %c%n   %l%n"), getServletContext().getRealPath(fileName), true);
            appender = new RollingFileAppender(new PatternLayout("[%-5p] %d (%l) - %m%n"), getServletContext().getRealPath(fileName), true);
        } catch (IOException ioe) { 
            System.out.println("Couldn't create appender for: " + fileName + ": " + ioe.getMessage());
        }
        return appender;
    }

    private void initLogger(String name, FileAppender appender, Level level) {
        //Logger logger;
        if (name == null) {            
            logger = Logger.getRootLogger();
        } else {
            logger = Logger.getLogger(name);
        }
        logger.setLevel(level);
        logger.addAppender(appender);
        logger.trace("Starting logger named " + logger.getName());
    }

    @Override
    public void destroy() {  
        logger.trace("Stopping logger named " + logger.getName());
        Logger.shutdown();
    }
}
