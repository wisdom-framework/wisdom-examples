package org.wisdom.examples.session.controllers;

import org.apache.felix.ipojo.annotations.Requires;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Body;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;
import org.wisdom.examples.session.api.Session;
import org.wisdom.examples.session.dtos.SessionDto;

/**
 * Created by subbu on 07/11/17.
 */
@Controller
public class SessionController extends DefaultController{

    /**
     * Injects a template named 'welcome'.
     */
    @View("session")
    Template sessionView;

    @Requires
    private Session session;

    /**
     * The action method returning the todo page.
     * It handles HTTP GET request on the "/session" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.GET, uri = "/session")
    public Result sessionView() {
        logger().info("Session - \n {}", session().getData());
        if(session().get("wisdom-session") == null) {
            logger().info("Creating a new session");
            String sessionId = session.create();
            session().put("wisdom-session", sessionId);
        }
        return ok(render(sessionView));
    }

    /**
     * The action to handle the create action of session obects
     * It handles HTTP POST request on the "/session/create" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.POST, uri = "/session/create")
    public Result createSession(@Body SessionDto sessionDto) {
        logger().info("todoDto {}", sessionDto.toString());
        String sessionId = session().get("wisdom-session");
        session.put(sessionId, sessionDto.getName(), sessionDto.getValue());
        session.dumpSession(sessionId);
        return redirect("/session");
    }
}
