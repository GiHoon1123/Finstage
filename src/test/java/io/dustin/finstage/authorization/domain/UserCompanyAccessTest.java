package io.dustin.finstage.authorization.domain;


import io.dustin.finstage.common.exception.custom.DuplicateCompanyAccessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import  static org.assertj.core.api.Assertions.*;

public class UserCompanyAccessTest {

    @Test
    @DisplayName("회사 접근 권한이 중복되지 않았을 때 정상적으로 업데이트 되는지 확인한다.")
    void shouldUpdateCompanyAccessSuccessfully(){

        // given
        Company company1 = Company.of(1L, "네이버");
        Company company2 = Company.of(2L, "삼성전자");
        UserCompanyAccess access = UserCompanyAccess.of("장득춘", "test@naver.com", List.of(company1));

        // when
        UserCompanyAccess updated = access.updateCompanyAccess(List.of(company1,company2));

        // then
        assertThat(updated.getCompanyAccess()).hasSize(2);
        assertThat(updated.getCompanyAccess())
                .extracting(Company::getCompanyName)
                .containsExactlyInAnyOrder("삼성전자","네이버");
    }

    @Test
    @DisplayName("중복된 회사 ID가 있을 때 예외가 발생하는지 확인한다.")
    void shouldThrow_whenDuplicatedCompanyIdsExist(){
        // given
        Company duplicate1 = Company.of(1L, "네이버");
        Company duplicate2 = Company.of(1L, "네이버");
        UserCompanyAccess access = UserCompanyAccess.of("장득춘", "test@naver.com", List.of());

        // when & then
        assertThatThrownBy(()->access.updateCompanyAccess(List.of(duplicate1,duplicate2)))
                .isInstanceOf(DuplicateCompanyAccessException.class);
    }
}
