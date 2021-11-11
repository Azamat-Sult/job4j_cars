package ru.job4j.cars.helper.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.cars.model.Post;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class PostSerializer implements JsonSerializer<Post> {

    @Override
    public JsonElement serialize(Post post,
                                 Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy HH:mm");
        JsonObject result = new JsonObject();
        result.addProperty("id", post.getId());
        result.addProperty("carBrand", post.getCarBrand().getCarBrand());
        result.addProperty("carModel", post.getCarModel().getCarModel());
        result.addProperty("bodyType", post.getBodyType().getBodyType());
        result.addProperty("bodyColor", post.getBodyColor());
        result.addProperty("mileAge", post.getMileAge());
        result.addProperty("ageYears", post.getAgeYears());
        result.addProperty("photo", post.getPhoto());
        result.addProperty("description", post.getDescription());
        result.addProperty("created", formater.format(post.getCreated()));
        result.addProperty("phone", post.getAuthor().getPhone());
        result.addProperty("email", post.getAuthor().getEmail());
        result.addProperty("showSoldButton", post.getShowSoldButton());
        return result;
    }
}