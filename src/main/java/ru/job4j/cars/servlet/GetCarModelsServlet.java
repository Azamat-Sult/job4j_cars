package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.helper.serializers.CarModelSerializer;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.service.CarsService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetCarModelsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(CarModel.class, new CarModelSerializer())
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        int id = Integer.parseInt(req.getParameter("id"));
        String json = GSON.toJson(CarsService.instOf().getModelsByBrandId(id));
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

}