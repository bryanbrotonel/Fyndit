package layout;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationDetail {
    private String name;
    private String address;
    private String imageName;
    private String json;

    private LocationDetail(String name, String address, String imageName, String json) {
        this.name = name;
        this.address = address;
        this.imageName = imageName;
        this.json = json;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImageName() {
        return imageName;
    }

    public String getJson() {
        return this.json;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return this.name;
    }

=======
>>>>>>> assets-issue-1
    public static LocationDetail fromJson(JSONObject o) throws JSONException {
        return new LocationDetail(o.getString("name"), o.getString("address"), o.getString("imageName"), o.toString());
    }
}
