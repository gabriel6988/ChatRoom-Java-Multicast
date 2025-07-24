# ChatRoom-Java-Multicast
O projeto é uma aplicação em Java que implemente um sistema de chat utilizando comunicação multicast. Para proteger a comunicação entre cliente e servidor, utilizamos **SSL/TLS**. Ele permite que vários clientes se conectem com um nome identificador e troquem mensagens com o servidor de forma cifrada.

## Integrantes
* Gabriel Alejandro Figueiro Galindo

## Funcionalidades
* Comunicação segura com SSL/TLS (protocolo TLS);
* Identificação de usuários por nome;
* Conversa com envio e resposta de mensagens em tempo real;
* Comando `exit` para o cliente encerrar a conexão;
* Servidor multithread com `Executors.newVirtualThreadPerTaskExecutor()`;

## Pré-requisitos
* Java 21 ou superior (recomendado por uso de virtual threads);
* `keytool` (incluso no JDK);
* Terminal ou IDE (como VS Code ou IntelliJ).
