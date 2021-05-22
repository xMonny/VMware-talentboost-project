package talentboost.vmware.utms.execution.syntax;

public interface InputCommandSyntax {

    int FOUR_ARGUMENTS = 4;
    int FIVE_ARGUMENTS = 5;
    int SIX_ARGUMENTS = 6;
    int SEVEN_ARGUMENTS = 7;
    int EIGHT_ARGUMENTS = 8;

    int FIRST_ARGUMENT = 0;
    int SECOND_ARGUMENT = 1;
    int THIRD_ARGUMENT = 2;
    int FOURTH_ARGUMENT = 3;
    int FIFTH_ARGUMENT = 4;
    int SIXTH_ARGUMENT = 5;
    int SEVENTH_ARGUMENT = 6;
    int EIGHT_ARGUMENT = 7;

    String CONFIG_FULL_COMMAND = "^--config$";
    String CONFIG_SHORT_COMMAND = "^-c$";
    /*String RUN_ID_FULL_COMMAND = "^--run-id$";
    String RUN_ID_SHORT_COMMAND = "^-r$";*/
    String SUITE_NAME_FULL_COMMAND = "^--suite-name$";
    String SUITE_NAME_SHORT_COMMAND = "^-sn$";
    String TEST_NAME_FULL_COMMAND = "^--test-name$";
    String TEST_NAME_SHORT_COMMAND = "^-tn$";
    String SERVER_SHORT_COMMAND = "^-s$";
    String SERVER_FULL_COMMAND = "^--server$";
    String DEBUG_SHORT_COMMAND = "^-d$";
    String DEBUG_FULL_COMMAND = "^--debug$";
}
