package io.dustin.salesmgmt.authorization.adapter.out.persistence;

import io.dustin.salesmgmt.authorization.domain.Company;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserCompanyAccessMapperTest {

    private final UserCompanyAccessMapper mapper = new UserCompanyAccessMapper();

    @Test
    @DisplayName("UserCompanyAccess를 List<UserCompanyAccessJpaEntity>로 변환할 수 있다")
    void shouldMapToJpaEntities() {
        // given
        UserCompanyAccess domain = UserCompanyAccess.of(
             "dustin",
             "dustin@naver.com",
             List.of(Company.of(1L,"TestCompany"))
        );

        // when
        List<UserCompanyAccessJpaEntity> entities = mapper.mapToJpaEntities(domain);

        // then
        assertThat(entities).hasSize(1);
        UserCompanyAccessJpaEntity entity = entities.getFirst();
        assertThat(entity.getUserEmail()).isEqualTo("dustin@naver.com");assertThat(entity.getCompanyId()).isEqualTo(1L);
        assertThat(entity.getCompanyName()).isEqualTo("TestCompany");
        assertThat(entity.getCreatedAt()).isNotNull(); // 생성 시각 확인

    }

    @Test
    @DisplayName("List<UserCompanyAccessJapEntity>를 UserCompanyAccessJpaEntity로 바꿀 수 있다.")
    void shouldMapToDomainEntity() {
        // given
        UserCompanyAccessJpaEntity entity = new UserCompanyAccessJpaEntity(
                1L,
                "dustin@naver.com",
                1L,
                "TestCompany",
                LocalDateTime.now()
        );

        // when
        UserCompanyAccess domain = mapper.mapToDomainEntity("dustin", "dustin@naver.com", List.of(entity));

        // then
        assertThat(domain.getUserName()).isEqualTo("dustin");
        assertThat(domain.getUserEmail()).isEqualTo("dustin@naver.com");
        assertThat(domain.getCompanyAccess()).hasSize(1);
        assertThat(domain.getCompanyAccess().getFirst().getCompanyId()).isEqualTo(1);
        assertThat(domain.getCompanyAccess().getFirst().getCompanyName()).isEqualTo("TestCompany");

    }
}
