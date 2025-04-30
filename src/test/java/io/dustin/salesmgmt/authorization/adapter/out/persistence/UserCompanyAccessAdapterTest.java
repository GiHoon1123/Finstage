package io.dustin.salesmgmt.authorization.adapter.out.persistence;

import io.dustin.salesmgmt.authorization.domain.Company;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@Import({UserCompanyAccessAdapter.class, UserCompanyAccessMapper.class})
public class UserCompanyAccessAdapterTest {

    @Autowired
    UserCompanyAccessAdapter adapter;

    @Autowired
    SpringDataUserCompanyAccessRepository repository;

    @Test
    @DisplayName("데이터의 정상 저장을 확인한다.")
    void save_shouldPersistEntities() {
        UserCompanyAccess domain = UserCompanyAccess.of("dustin", "dustin@naver.com", List.of(Company.of(1L, "회사A")));

        adapter.save(domain);

        boolean exists = repository.existsByUserEmailAndCompanyIdIn("dustin@naver.com",List.of(1L));
        assertThat(exists).isTrue();
    }

    @Test
    void findExistingCompanyAccesses_shouldReturnCorrectCompanies() {

        repository.save(new UserCompanyAccessJpaEntity(
                null, "dustin@naver.com", 1L, "회사A", LocalDateTime.now()
        ));

        UserCompanyAccess result = adapter.findExistingCompanyAccesses(
                "dustin", "dustin@naver.com", List.of(1L)
        );

        assertThat(result.getUserEmail()).isEqualTo("dustin@naver.com");
        assertThat(result.getCompanyAccess()).hasSize(1);
        assertThat(result.getCompanyAccess().getFirst().getCompanyId()).isEqualTo(1L);
    }

    @Test
    void existsByUserEmailAndCompanyIds_shouldReturnFalseIfNoneExists() {
        boolean result = adapter.existsByUserMailAndCompanyIds("notfound@naver.com", List.of(999L));
        assertThat(result).isFalse();
    }


}
