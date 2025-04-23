package io.dustin.salesmgmt.authorization.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class CompanyTest {

    @Test
    @DisplayName("Company 엔티티가 정상적으로 생성되는지 확인한다.")
    void shouldReturnCompanySuccessfully(){

        // given
        Long companyId = 1L;
        String companyName = "테스트 회사";

        // when
        Company company = Company.of(companyId,companyName);

        // then
        assertThat(company.getCompanyId()).isEqualTo(1L);
        assertThat(company.getCompanyName()).isEqualTo("테스트 회사");
    }


}
