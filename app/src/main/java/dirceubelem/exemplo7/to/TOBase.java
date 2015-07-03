package dirceubelem.exemplo7.to;


import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TOBase implements Cloneable {

    private transient Object tag;

    public TOBase() {
    }

    public TOBase(Object tag) {
        this.tag = tag;
    }

    private static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(Calendar.class,
                new CalendarDeserializer());
        gsonBuilder.registerTypeHierarchyAdapter(Calendar.class,
                new CalendarSerializer());

        Gson gson = gsonBuilder.create();

        return gson;
    }

    public static <T extends TOBase> T createByJson(String json, Class<T> c) {
        try {
            Gson gson = getGson();
            return gson.fromJson(json, c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        Gson gson = getGson();
        return gson.toJson(this, getClass());
    }

    protected static class CalendarDeserializer implements
            JsonDeserializer<Calendar> {

        @SuppressLint("UseValueOf")
        public Calendar deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
            String JSONDateToMilliseconds = "\\/(Date\\((-*.*?)([\\+\\-].*)?\\))\\/";

            Pattern pattern = Pattern.compile(JSONDateToMilliseconds);

            Matcher matcher = pattern.matcher(json.getAsJsonPrimitive()
                    .getAsString());
            matcher.matches();

            String tzone = matcher.group(3);
            String result = matcher.replaceAll("$2");

            Calendar calendar = new GregorianCalendar();
            calendar.setTimeZone(TimeZone.getTimeZone("GMT" + tzone));
            calendar.setTimeInMillis(new Long(result));

            return calendar;
        }
    }

    protected static class CalendarSerializer implements
            JsonSerializer<Calendar> {

        @Override
        public JsonElement serialize(Calendar calendar, Type typeOfT,
                                     JsonSerializationContext context) {
            return new JsonPrimitive("/Date(" + calendar.getTimeInMillis()
                    + ")/");
        }
    }
}