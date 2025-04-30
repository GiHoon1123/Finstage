package io.dustin.finstage.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class SlackNotifierTest {

    @Test
    @DisplayName("send 메서드는 정상적으로 RestTemplate을 호출해야 한다")
    void shouldSendSlackMessage() {
        // given
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        SlackNotifier slackNotifier = new SlackNotifier(mockRestTemplate);

        // 생성된 인스턴스의 내부 필드를 강제로 세팅한다.
        ReflectionTestUtils.setField(slackNotifier, "webhookUrl", "https://hooks.slack.com/services/test");

        // when
        slackNotifier.send("테스트 메시지");

        // then
        verify(mockRestTemplate, times(1))
                .postForEntity(
                        anyString(),
                        any(HttpEntity.class),
                        eq(String.class)
                );
    }
}
