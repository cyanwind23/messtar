package com.thiennam.messtar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class MesstarApplicationTests {

	@Test
	void contextLoads() {
		Long a = 10L;
		Long b = 10L;
		a.equals(b);
		System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(10L), ZoneId.systemDefault()));
	}

}
