package ru.job4j.cars.helper.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.cars.model.CarBrand;

import java.lang.reflect.Type;

public class CarBrandSerializer implements JsonSerializer<CarBrand> {
    @Override
    public JsonElement serialize(CarBrand carBrand,
                                 Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("id", carBrand.getId());
        result.addProperty("carBrand", carBrand.getCarBrand());
        return result;
    }
}