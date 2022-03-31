package library.exeptions;

public class BookOutOfStock extends Exception{

    public BookOutOfStock(String msg) {
        super(msg);
    }

}
