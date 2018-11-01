package top.wonderheng.student.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.listener
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:39
 */
@WebListener
public class ApplicationConfigListener implements ServletContextListener {

    public static DbConfig dbConfig;

    private static Logger logger = LoggerFactory.getLogger(ApplicationConfigListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String jdbcUrl = servletContext.getInitParameter("jdbc_url");
        String jdbcUsername = servletContext.getInitParameter("jdbc_username");
        String jdbcPassword = servletContext.getInitParameter("jdbc_password");

        dbConfig = new DbConfig(jdbcUrl, jdbcUsername, jdbcPassword);

        logger.debug("Application configuration parameter load ok");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


    public static class DbConfig {

        private final String jdbcUrl;
        private final String jdbcUsername;
        private final String jdbcPassword;


        public DbConfig(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
            this.jdbcUrl = jdbcUrl;
            this.jdbcUsername = jdbcUsername;
            this.jdbcPassword = jdbcPassword;
        }

        public String getJdbcUrl() {
            return jdbcUrl;
        }

        public String getJdbcUsername() {
            return jdbcUsername;
        }

        public String getJdbcPassword() {
            return jdbcPassword;
        }
    }
}

