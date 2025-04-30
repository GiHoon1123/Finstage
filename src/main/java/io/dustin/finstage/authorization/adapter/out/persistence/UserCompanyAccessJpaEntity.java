package io.dustin.finstage.authorization.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user_company_access",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_email_company_id",
                        columnNames = {"userEmail", "companyId"}
                )
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyAccessJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private Long companyId;

    private String companyName;

    private LocalDateTime createdAt;
}
