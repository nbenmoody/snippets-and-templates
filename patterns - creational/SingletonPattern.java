
public class SingletonPattern {

    // Uses volatile to ensure that this remains a singleton
    // …for some reason.
    private static volatile SingletonPattern instance = null;

    private SingletonPattern() {
        if(instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    // This is a customary method & name in a Singleton class.
    public static SingletonPattern getInstance() {

        // Loads lazily, only if called for.
        // This is also now thread safe, using “synchronized" only if the object hasn’t been instantiated yet, and double-checking once “synchronized” is called to make sure another thread hasn’t created it yet.
        if(instance == null) {
            synchronized(SingletonPattern.class) {
                if(instance == null) {
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }



    // Example:
    public static void main(String[] args) {

        // This gets the single instance, using the static method:
        SingletonPattern myInstance = SingletonPattern.getInstance();
        System.out.println(myInstance);

        // This throws a compiler error, because the constructor is private. The object cannot be
        // instantiated directly, to ensure it remains a singleton.
        // SingletonPattern testConst = new SingletonPattern();

        // This will return the same memory address, because the instance
        // is exactly the same object.
        SingletonPattern sameInstance = SingletonPattern.getInstance();
        System.out.println(sameInstance);
    }

}
