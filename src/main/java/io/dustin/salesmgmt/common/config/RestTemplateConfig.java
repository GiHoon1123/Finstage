package io.dustin.salesmgmt.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTempate를 매번 인스턴스를 생성하지 말고 Bean을 통해 컨테이너에 등록 후 다른 클래스에서 주입하여 사용한다. 장점은 아래와 같다.
 * 1. 여러 클래스에서 RestTemplate을 공통으로 재사용할 수 있다. (매번 new RestTemplate() 안 해도 됨
 * 2. 타임아웃(timeout), 인터셉터(logging), 에러 핸들링 등 추가 설정을 한 번만 설정해서 쓸 수 있다.
 * 3. 필요하면 설정만 수정하면 전체 RestTemplate 동작이 바뀐다. (코드 수정 없이)
 * 4. 테스트할 때 Mocking이 쉽다. (MockRestServiceServer 같은 걸로 쉽게 가짜 응답 설정 가능)
 * 5. 스프링 컨테이너가 관리하기 때문에 성능, 안정성 면에서도 좋다.
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
