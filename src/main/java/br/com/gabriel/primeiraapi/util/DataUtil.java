package br.com.gabriel.primeiraapi.util;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DataUtil {
	
	 public static LocalDate dataAtual() {
		  	LocalDate dataAtual = LocalDate.now();
			return dataAtual;
	  }
}
