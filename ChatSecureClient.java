import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class ChatSecureClient {
  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = 16001;

  public static void main(String[] args) throws IOException {

    // Socket SSL para comunicação segura com o servidor
    SSLSocket socket = null;
    try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));) {

      // Carrega o keystore com certificados confiáveis
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new FileInputStream("keystore.jks"), "shulambs".toCharArray());

      // Inicializa o gerenciador de confiança com o keystore
      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(keyStore);

      // Configura o contexto SSL com os gerenciadores de confiança
      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, tmf.getTrustManagers(), null);

      // Cria uma fábrica de sockets SSL e conecta ao servidor
      SSLSocketFactory ssf = sslContext.getSocketFactory();
      socket = (SSLSocket) ssf.createSocket(SERVER_ADDRESS, SERVER_PORT);

      // Inicializa streams para envio e recebimento de dados
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Envio de dados ao servidor
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Recebimento de dados

      // Lê o nome do cliente
      System.out.print("Digite seu nome: ");
      String clientName = userInput.readLine();
      out.println(clientName);

      System.out.println("Servidor: Conectado ao servidor como " + clientName + ". Digite suas mensagens (ou 'exit' para encerrar):");

      // Loop principal de comunicação
      String input;
      while ((input = userInput.readLine()) != null) {
        // Caso o usuário insira "exit", ele saíra do ChatRoom
        if (input.equalsIgnoreCase("exit")) {
          out.println(input);
          System.out.println("Servidor: Você saiu do chat. Até mais!");
          in.close();
          out.close();
          break;
        }
        out.println(input); // Envia a mensagem digitada pelo usuário ao servidor
        System.out.println("Servidor: " + in.readLine()); // Exibe a resposta recebida do servidor
      }
    } catch (Exception e) {
      System.out.println("Erro no cliente: " + e.getMessage());
    } 
    finally {
      if (socket != null) {
        socket.close();
      }
    }
  }
}