package io.dustin.finstage.authorization.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

@DataJpaTest
public class UserCompanyAccessJpaEntityTest {

    @Autowired
    private SpringDataUserCompanyAccessRepository repository;


    @Test
    @DisplayName("데이터를 저장하고 조회한다.")
    void shouldSaveAndFindUserCompanyAccess() {

        // given
        UserCompanyAccessJpaEntity entity = new UserCompanyAccessJpaEntity(
                null,
                "test@naver.com",
                1L,
                "Test Company",
                LocalDateTime.now()
        );

        // when
        UserCompanyAccessJpaEntity saveEntity = repository.save(entity);
        UserCompanyAccessJpaEntity foundEntity = repository.findById(saveEntity.getId())
                .orElseThrow(()-> new RuntimeException("엔티티를 찾을 수 없습니다."));


        // then
        assertThat(foundEntity.getUserEmail()).isEqualTo("test@naver.com");
        assertThat(foundEntity.getCompanyId()).isEqualTo(1L);
        assertThat(foundEntity.getCompanyName()).isEqualTo("Test Company");
    }

    @Test
    @DisplayName("userEmail과 companyId는 유니크해야 한다.")
    void shouldNotAllowDuplicateUserEmailAndCompanyId(){
        // given
        UserCompanyAccessJpaEntity entity1 = new UserCompanyAccessJpaEntity(
                null,
                "test@naver.com",
                1L,
                "Company A",
                LocalDateTime.now()
        );
        repository.save(entity1);

        UserCompanyAccessJpaEntity entity2 = new UserCompanyAccessJpaEntity(
                null,
                "test@naver.com",
                1L,
                "Company B",
                LocalDateTime.now()
        );

        // when & then
        assertThatThrownBy(()-> repository.saveAndFlush(entity2))
                .isInstanceOf(DataIntegrityViolationException.class);

    }



}
