package ru.job4j.cars.helper.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.cars.model.CarModel;

import java.lang.reflect.Type;

public class CarModelSerializer implements JsonSerializer<CarModel> {
    @Override
    public JsonElement serialize(CarModel carModel,
                                 Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("id", carModel.getId());
        result.addProperty("carModel", carModel.getCarModel());
        return result;
    }
}