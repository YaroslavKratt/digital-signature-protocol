import processor.CommandArgumentProcessor;

public class App {

    public static void main(String[] args) {
        CommandArgumentProcessor commandArgumentProcessor = new CommandArgumentProcessor(args[0], args);
        commandArgumentProcessor.process();

    }
}
