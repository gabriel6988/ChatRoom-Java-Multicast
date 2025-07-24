# ChatRoom-Java-Multicast
O projeto é uma aplicação em Java que implementa um sistema de chat utilizando comunicação multicast. Ele permite que vários clientes se conectem com um nome identificador e troquem mensagens com o servidor de forma cifrada. Para proteger a comunicação entre cliente e servidor, foi utilizado **SSL/TLS**.

## Integrantes
* Gabriel Alejandro Figueiro Galindo

## Funcionalidades
* Comunicação segura com **SSL/TLS** (protocolo TLS);
* Identificação de usuários por nome;
* Conversa com envio e resposta de mensagens em tempo real;
* Comando `exit` para o cliente encerrar a conexão;
* Servidor multithread com `Executors.newVirtualThreadPerTaskExecutor()`;

## Pré-requisitos
* Java 21 ou superior (recomendado por uso de virtual threads);
* `keytool` (incluso no JDK);
* Terminal ou IDE (como VS Code ou IntelliJ).

## Como executar
1. Compile os arquivos: `javac ChatSecureServer.java ChatSecureClient.java`
2. Gere um keystore: `keytool -genkeypair -alias serverkey \
  -keyalg RSA -keysize 2048 \
  -keystore keystore.jks \
  -validity 365 \
  -storepass shulambs`
3. Inicie o servidor: `java ChatSecureServer`
4. Em outro terminal, inicie o cliente: `java ChatSecureClient`
