package talentboost.vmware.utms.tool.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrettyGson {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .create();
}
