package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.helper.serializers.PostSerializer;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarsService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetPostsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder()
            .setDateFormat("dd/MM/yy HH:mm")
            .registerTypeAdapter(Post.class, new PostSerializer())
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        User sessionUser = (User) req.getSession().getAttribute("user");
        int carBrandIdFilter = Integer.parseInt(req.getParameter("carBrandIdFilter"));
        Boolean showTodayPosts = Boolean.parseBoolean(req.getParameter("showTodayPosts"));
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(CarsService.instOf().findAllActivePosts(sessionUser,
                carBrandIdFilter, showTodayPosts));
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
