package sn.intouch.gu.api.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtils {
	public static Gson gson = new Gson();
	static {
	    GsonBuilder builder = new GsonBuilder();
	    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

	        @Override
	        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	                throws JsonParseException {
	            String date = json.getAsJsonPrimitive().getAsString();
	            String myFormat = "yyyy-MM-dd hh:mm:ss";
	            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);


	            try {
	                return sdf.parse(date);
	            } catch (ParseException e) {

	                e.printStackTrace();
	            }
	            return new Date();
	        }
	    });

	    builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
	        public JsonElement serialize(Date date, Type typeOfId, JsonSerializationContext context) {


	            return new JsonPrimitive(date.getTime());
	        }

	    });

	    gson = builder.create();
	}

}
