import factory.ServerFactory;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Initializing application...");
        ServerFactory.setup();
        System.out.println("Finishing application...");
    }
}
