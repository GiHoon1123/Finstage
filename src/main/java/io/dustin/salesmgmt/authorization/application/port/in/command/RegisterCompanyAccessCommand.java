package io.dustin.salesmgmt.authorization.application.port.in.command;

import io.dustin.salesmgmt.common.validation.StringOnly;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterCompanyAccessCommand {

    @NotEmpty(message = "userName은 필수 입력입니다.")
    @StringOnly
    private String userName;

    @NotEmpty(message = "userEmail은 필수 입력입니다.")
    @Email(message = "userEmail은 이메일 형식이어야 합니다.")
    private String userEmail;

    @NotEmpty(message = "companies 목록은 비어있을 수 없습니다.")
    @Valid
    private List<CompanyInfo> companies;

    public RegisterCompanyAccessCommand(String userName, String userEmail, List<CompanyInfo> companies) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.companies = companies;
    }

    @Getter
    @NoArgsConstructor
    public static class CompanyInfo {

        @NotNull(message = "companyId는 필수 입력입니다.")
        @Positive(message = "companyId는 양수여야 합니다.")
        private Long companyId;

        @NotEmpty(message = "companyName은 필수 입력입니다.")
        @StringOnly(message = "companyName은 문자(한글, 영문)와 공백만 입력 가능합니다. ")
        private String companyName;

        public CompanyInfo(Long companyId, String companyName) {
            this.companyId = companyId;
            this.companyName = companyName;
        }
    }
}
