package talentboost.vmware.utms.tool.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import talentboost.vmware.utms.structure.Plan;
import talentboost.vmware.utms.tool.yaml.exception.ReadYAMLFileException;

import java.io.*;

import static talentboost.vmware.utms.message.Message.*;

public class YamlTool {

    private static final String DASH_PATTERN = "- ";
    private static final String EMPTY_STRING = "";

    public static String parseToJson(String yamlContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Object obj = mapper.readValue(yamlContent, Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }

    public static String readYaml(String pathToYamlFile) throws ReadYAMLFileException {
        if (pathToYamlFile == null) {
            throw new ReadYAMLFileException(NULL_FILE_ERROR_MESSAGE);
        }
        File yamlFile = new File(pathToYamlFile);

        StringBuilder yamlContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(yamlFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                yamlContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ReadYAMLFileException(FILE_READ_PERMS_ERROR_MESSAGE);
        }
        return yamlContent.toString();
    }

    public static String prepareForClassParsing(String pathToYamlFile) throws ReadYAMLFileException {
        String yamlContent = readYaml(pathToYamlFile);
        return yamlContent.replaceAll(DASH_PATTERN, EMPTY_STRING);
    }

    public static Plan parseYAMLToPlan(String yamlContent) {
        Yaml yaml = new Yaml();
        return yaml.loadAs(yamlContent, Plan.class);
    }
}
