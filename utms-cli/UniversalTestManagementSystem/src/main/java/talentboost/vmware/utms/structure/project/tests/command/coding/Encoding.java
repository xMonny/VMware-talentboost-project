package talentboost.vmware.utms.structure.project.tests.command.coding;

import org.apache.commons.codec.binary.Base64;

public class Encoding {

    public static String encodeToBase64(String str) {
        Base64 base64 = new Base64();
        return new String(base64.encode(str.getBytes()));
    }
}
