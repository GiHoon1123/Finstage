package io.dustin.salesmgmt.user.application.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MsGraphUserInfoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("Json 데이터를 역직렬화 하여 Dto에 잘 매핑되는지 테스트한다.")
    void shouldDeserializeJsonToMsGraphUserInfo() throws JsonProcessingException {

        // given
        String json = """
                            {
                              "name": "Development Team",
                              "members": [
                                { "displayName": "Alice Kim", "mail": "alice.kim@naver.com" },
                                { "displayName": "Bob Lee", "mail": "bob.lee@naver.com" }
                              ]
                            }
                """;

        // when
        MsGraphUserInfo info = objectMapper.readValue(json, MsGraphUserInfo.class);

        // then
        assertThat(info.name()).isEqualTo("Development Team");
        assertThat(info.members()).hasSize(2);
        assertThat(info.members().getFirst().displayName()).isEqualTo("Alice Kim");
        assertThat(info.members().getFirst().mail()).isEqualTo("alice.kim@naver.com");
    }

    @Test
    @DisplayName("Dto 객체가 잘 생성되는지 확인한다.")
    void shouldCreateInstanceManually(){
        MsGraphUserInfo.Member member = new MsGraphUserInfo.Member("dustin", "dustin@naver.com");
        MsGraphUserInfo info = new MsGraphUserInfo("Test dept", List.of(member));



    }



}
