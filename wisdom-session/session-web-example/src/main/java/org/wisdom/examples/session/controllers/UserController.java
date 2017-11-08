package org.wisdom.examples.session.controllers;

import org.apache.felix.ipojo.annotations.Requires;
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
import org.wisdom.examples.session.dtos.UserDto;

/**
 * Created by subbu on 07/11/17.
 */
@Controller
public class UserController extends DefaultController{

    /**
     * Injects a template named 'welcome'.
     */
    @View("user")
    Template userView;

    @Requires
    private Session session;

    /**
     * The action method returning the todo page.
     * It handles HTTP GET request on the "/user" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.GET, uri = "/user")
    public Result userView() {
        logger().debug("About the render the User View...");
        logger().debug("Session - \n {}", session().getData());
        if(session().get("wisdom-session") == null || !session.exists(session().get("wisdom-session"))) {
            logger().debug("Creating a new session");
            String sessionId = session.create();
            session().put("wisdom-session", sessionId);
        }
        return ok(render(userView));
    }

    /**
     * The action to handle the create action of session obects
     * It handles HTTP POST request on the "/session/create" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.POST, uri = "/user/create")
    public Result createUser(@Body UserDto userDto) {
        logger().debug("todoDto {}", userDto.toString());
        String sessionId = session().get("wisdom-session");
        session.put(sessionId, "userDetails", userDto);
        return redirect("/session");
    }
}
