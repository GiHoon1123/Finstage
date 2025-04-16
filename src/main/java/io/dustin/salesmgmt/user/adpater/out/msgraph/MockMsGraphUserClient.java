package io.dustin.salesmgmt.user.adpater.out.msgraph;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dustin.salesmgmt.user.application.dto.MsGraphUserInfo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MockMsGraphUserClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String mockJson = """
              [
                {
                  "name": "Development Team",
                  "members": [
                    { "displayName": "Alice Kim", "mail": "alice.kim@naver.com" },
                    { "displayName": "Bob Lee", "mail": "bob.lee@naver.com" },
                    { "displayName": "Charlie Hwang", "mail": "charlie.hwang@naver.com" },
                    { "displayName": "Daisy Park", "mail": "daisy.park@naver.com" },
                    { "displayName": "Eva Shin", "mail": "eva.shin@naver.com" },
                    { "displayName": "Frank Cho", "mail": "frank.cho@naver.com" },
                    { "displayName": "Grace Kang", "mail": "grace.kang@naver.com" },
                    { "displayName": "Henry Ryu", "mail": "henry.ryu@naver.com" }
                  ]
                },
                {
                  "name": "Regional Sales Team",
                  "members": [
                    { "displayName": "Bob Lee", "mail": "bob.lee@naver.com" },
                    { "displayName": "Irene Yang", "mail": "irene.yang@naver.com" },
                    { "displayName": "Jane Kim", "mail": "jane.kim@naver.com" },
                    { "displayName": "Kevin Wi", "mail": "kevin.wi@naver.com" }
                  ]
                },
                {
                  "name": "Corporate Sales Team",
                  "members": [
                    { "displayName": "Bob Lee", "mail": "bob.lee@naver.com" },
                    { "displayName": "Irene Yang", "mail": "irene.yang@naver.com" },
                    { "displayName": "Luna Shin", "mail": "luna.shin@naver.com" },
                    { "displayName": "Mike Lee", "mail": "mike.lee@naver.com" },
                    { "displayName": "Nina Song", "mail": "nina.song@naver.com" },
                    { "displayName": "Oscar Oh", "mail": "oscar.oh@naver.com" },
                    { "displayName": "Paul Nam", "mail": "paul.nam@naver.com" }
                  ]
                },
                {
                  "name": "Support Team",
                  "members": [
                    { "displayName": "Bora Lee", "mail": "bora.lee@naver.com" },
                    { "displayName": "Bob Lee", "mail": "bob.lee@naver.com" },
                    { "displayName": "Heesun Jang", "mail": "heesun.jang@naver.com" },
                    { "displayName": "Juyeon Kim", "mail": "juyeon.kim@naver.com" },
                    { "displayName": "Chaewon Kim", "mail": "chaewon.kim@naver.com" },
                    { "displayName": "Jieun Jung", "mail": "jieun.jung@naver.com" },
                    { "displayName": "Yumi Lee", "mail": "yumi.lee@naver.com" },
                    { "displayName": "Eunseon Lee", "mail": "eunseon.lee@naver.com" },
                    { "displayName": "Seungha Woo", "mail": "seungha.woo@naver.com" },
                    { "displayName": "Soohyun Park", "mail": "soohyun.park@naver.com" }
                  ]
                },
                {
                  "name": "Finance Team",
                  "members": [
                    { "displayName": "Dongjoon Shin", "mail": "dongjoon.shin@naver.com" }
                  ]
                }
              ]
            """;

    public String getRawJson() {
        return mockJson;
    }

    public List<MsGraphUserInfo> getAllDepartments() {
        try {
            return objectMapper.readValue(
                    mockJson,
                    new TypeReference<>() {
                    }
            );
        } catch (IOException error) {
            throw new RuntimeException("Failed to parse mock JSON", error);
        }

    }

    public List<MsGraphUserInfo> getDepartmentsByName(List<String> departmentNames) {
        List<MsGraphUserInfo> all = getAllDepartments();
        return all.stream()
                .filter(dept -> departmentNames.contains(dept.name()))
                .toList();
    }
}
