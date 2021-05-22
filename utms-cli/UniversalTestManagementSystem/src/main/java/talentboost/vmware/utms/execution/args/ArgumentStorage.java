package talentboost.vmware.utms.execution.args;

public class ArgumentStorage {

    private String configFile;
    private String serverUrl;
    private String testName;
    private String suiteName;
    private String debugFlag;

    public String getConfigFile() {
        return configFile;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getTestName() {
        return testName;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public String getDebugFlag() {
        return debugFlag;
    }

    private ArgumentStorage() {}

    public static class ArgumentStorageBuilder {
        private String configFile;
        private String serverUrl;
        private String testName;
        private String suiteName;
        private String debugFlag;

        public ArgumentStorageBuilder setConfigFile(String configFile) {
            this.configFile = configFile;
            return this;
        }

        public ArgumentStorageBuilder setSeverUrl(String serverUrl) {
            this.serverUrl = serverUrl;
            return this;
        }

        public ArgumentStorageBuilder setTestName(String testName) {
            this.testName = testName;
            return this;
        }

        public ArgumentStorageBuilder setSuiteName(String suiteName) {
            this.suiteName = suiteName;
            return this;
        }

        public ArgumentStorageBuilder setDebugFlag(String debugFlag) {
            this.debugFlag = debugFlag;
            return this;
        }

        public ArgumentStorage build() {
            ArgumentStorage argumentStorage = new ArgumentStorage();
            argumentStorage.configFile = this.configFile;
            argumentStorage.serverUrl = this.serverUrl;
            argumentStorage.testName = this.testName;
            argumentStorage.suiteName = this.suiteName;
            argumentStorage.debugFlag = this.debugFlag;
            return argumentStorage;
        }
    }
}
