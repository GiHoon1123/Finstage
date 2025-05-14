package io.dustin.finstage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")  // 꼭 명시해야 함!
@SpringBootTest
class FinstageApplicationTests {

	@Test
	void contextLoads() {
	}

}
