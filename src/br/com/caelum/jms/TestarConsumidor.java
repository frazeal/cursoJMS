package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TestarConsumidor {
	
	static final int timeOut = 10000;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		InitialContext context = new InitialContext();

		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila);
		
		Message message = consumer.receive(timeOut);
		System.out.println("Recebendo a mensagem:" + message);
		
		session.close();
		connection.close();
		context.close();
	}

}
