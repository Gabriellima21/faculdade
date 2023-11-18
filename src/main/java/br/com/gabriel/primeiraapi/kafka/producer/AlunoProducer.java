package br.com.gabriel.primeiraapi.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabriel.primeiraapi.entity.Aluno;

@Component
public class AlunoProducer {
	
	@Value("${topico.aluno}") //guardo o meu nome do meu topico na string topico
	private String topico;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;  //instância para enviar mensagens, chave e valor String
	
	@Autowired
	private ObjectMapper objectMapper; //transformar obj em JSON
	
	public void enviar (Aluno aluno) {
		try {
			kafkaTemplate.send(topico, objectMapper.writeValueAsString(aluno)); //writeValueAsString converter objetos Java em formato JSON (serialização).
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Erro ao enviar mensagem", e);
		}
	}
}
