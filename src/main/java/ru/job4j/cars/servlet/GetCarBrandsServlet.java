package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.helper.serializers.CarBrandSerializer;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.service.CarsService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetCarBrandsServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(CarBrand.class, new CarBrandSerializer())
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(CarsService.instOf().getAllCarBrands());
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}