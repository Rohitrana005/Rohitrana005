package com.trainingsite.core.servlets;


import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;


import com.trainingsite.core.services.impl.SignupService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.jetbrains.annotations.NotNull;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component( immediate = true, service = Servlet.class, property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths=" + "/bin/signupservlet" })
public class SignupServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 4438376868274173005L;
    @Reference
    SignupService form;

    private static final Logger log = LoggerFactory.getLogger(SignupServlet.class);


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

                try {

                         log.info("Reading the data from the webservice");

                    String firstname = request.getParameter("firstName");
                    String lastname = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String psw = request.getParameter("password");
                    String gender = request.getParameter("gender");
                    String dob = request.getParameter("birthDate");
                    String address = request.getParameter("address");

            log.info("calling service");

            boolean result=form.isAccessible();
            response.getWriter().println(result);



        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }


    }
}