package talentboost.vmware.utms.structure.project.tests.command;

import talentboost.vmware.utms.structure.project.status.Status;
import talentboost.vmware.utms.structure.project.tests.command.coding.Encoding;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;
import talentboost.vmware.utms.structure.project.tests.command.formatter.DateFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecutor {

    private static final String PROCESS_START_ERROR_MESSAGE = "Process of command could not start.";
    private static final String INTERRUPTED_THREAD_ERROR_MESSAGE = "Command cannot be executed because "
            + "process thread was interrupted.";
    private static final String NULL_PROCESS_ERROR_MESSAGE = "Detected null process.";
    private static final String NULL_MESSAGE_TYPE_ERROR_MESSAGE = "Command output or error cannot be extracted: "
            + "Detected null message type.";
    private static final String COMMAND_MESSAGE_EXTRACT_MESSAGE = "Cannot extract output or error message.";

    public static CommandData extractCommandDataFrom(String command) throws InvalidMessageTypeException, InterruptedThreadException, ProcessIOException, MessageReaderException {
        long millis = System.currentTimeMillis();
        DateFormatter startDate = new DateFormatter(millis);
        Process process = executeCommand(command);
        millis = System.currentTimeMillis();
        DateFormatter endDate = new DateFormatter(millis);

        int exitStatus = getExitStatus(process);
        Status status = defineStatusBy(exitStatus);
        String output = getOutput(process);
        String encodedOutput = Encoding.encodeToBase64(output);
        String error = getErrorMessage(process);
        String encodedError = Encoding.encodeToBase64(error);

        if (exitStatus != 200) {
            if (error.isEmpty()) {
                error = output;
                output = "";
            }
        }

        CommandData.CommandDataBuilder commandDataBuilder = new CommandData.CommandDataBuilder();
        return commandDataBuilder.setStatus(status)
                .setOutput(output)
                .setEncodedOutput(encodedOutput)
                .setError(error)
                .setEncodedError(encodedError)
                .setStartDate(startDate.toString())
                .setEndDate(endDate.toString())
                .build();
    }

    private static Process executeCommand(String command) throws ProcessIOException, InterruptedThreadException {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return process;
        } catch (IOException e) {
            throw new ProcessIOException(PROCESS_START_ERROR_MESSAGE);
        } catch (InterruptedException e) {
            throw new InterruptedThreadException(INTERRUPTED_THREAD_ERROR_MESSAGE);
        }

    }

    private static int getExitStatus(Process process) {
        if (process == null) {
            throw new IllegalArgumentException(NULL_PROCESS_ERROR_MESSAGE);
        }
        return process.exitValue();
    }

    private static Status defineStatusBy(int exitStatus) {
        if (exitStatus == 0) {
            return Status.PASSED;
        }
        return Status.FAILED;
    }

    private static InputStreamReader getInputStreamReader(Process process, CommandMessageType commandMessageType) throws InvalidMessageTypeException {
        if (commandMessageType == null) {
            throw new InvalidMessageTypeException(NULL_MESSAGE_TYPE_ERROR_MESSAGE);
        }
        switch (commandMessageType) {
            case OUTPUT:
                return new InputStreamReader(process.getInputStream());
            case ERROR:
                return new InputStreamReader(process.getErrorStream());
            default:
                throw new InvalidMessageTypeException(COMMAND_MESSAGE_EXTRACT_MESSAGE);
        }
    }

    private static String getMessage(Process process, CommandMessageType commandMessageType) throws InvalidMessageTypeException, MessageReaderException {
        InputStreamReader isr = getInputStreamReader(process, commandMessageType);

        StringBuilder message = new StringBuilder();
        try (BufferedReader br = new BufferedReader(isr)) {
            String line = "";
            while ((line = br.readLine()) != null) {
                message.append(line);
            }
        } catch(IOException e) {
            throw new MessageReaderException(COMMAND_MESSAGE_EXTRACT_MESSAGE);
        }
        return message.toString();
    }

    private static String getOutput(Process process) throws InvalidMessageTypeException, MessageReaderException {
        return getMessage(process, CommandMessageType.OUTPUT);
    }

    private static String getErrorMessage(Process process) throws InvalidMessageTypeException, MessageReaderException {
        return getMessage(process, CommandMessageType.ERROR);
    }
}
