package library.exeptions;

public class ContactAlreadyExists extends Exception {
    public ContactAlreadyExists(String msg) {
        super(msg);
    }
}
