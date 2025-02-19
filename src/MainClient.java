public class MainClient {
    public static void main(String[] args) {
       Client c1 = new Client("Ciao");
       c1.connetti("localhost",2000);
       c1.scrivi("Hey Server");
    }
}

