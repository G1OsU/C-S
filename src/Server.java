import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;
    private InputStream in;
    private OutputStream out;

    public Server(int porta) {
        this.porta = porta;
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Connessione Riuscita: " + porta);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore connessione porta");
        }
    }

    //attende la connessione di un client
    public Socket attendi(){
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Connessione ricevuta da: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientSocket;
    }




    //chiude le connessioni al server
    public void chiudi() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leggi() {
        InputStream inputStream = null;
        String message = null;

        try {
            //ottiene flusso dati del socket client
            inputStream = clientSocket.getInputStream();
            //crea un buffer per prendere byte e poi convertirle in caratteri (InputStreamReader)
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (message != null) {
            System.out.println("Messaggio dal client: " + message);
        } else {
            System.out.println("Nessun messaggio ricevuto dal client.");
        }
    }


}

