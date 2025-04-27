package io.dustin.salesmgmt.authorization.application.service;

import io.dustin.salesmgmt.authorization.adapter.out.persistence.LoadUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.application.port.in.RegisterCompanyAccessUseCase;
import io.dustin.salesmgmt.authorization.application.port.in.command.RegisterCompanyAccessCommand;
import io.dustin.salesmgmt.authorization.application.port.out.RegisterUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.domain.Company;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import io.dustin.salesmgmt.common.annotation.UseCase;
import io.dustin.salesmgmt.common.exception.custom.AlreadyRegisteredCompanyException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class RegisterCompanyAccessService implements RegisterCompanyAccessUseCase {

    private final RegisterUserCompanyAccessPort registerUserCompanyAccessPort;
    private final LoadUserCompanyAccessPort loadUserCompanyAccessPort;

    @Override
    public boolean register(RegisterCompanyAccessCommand command) {

        List<Company> companies = command.getCompanies().stream()
                .map(c -> Company.of(c.getCompanyId(), c.getCompanyName()))
                .toList();

        UserCompanyAccess existingAccess = loadUserCompanyAccessPort.findExistingCompanyAccesses(
                command.getUserName(),
                command.getUserEmail(),
                companies.stream().map(Company::getCompanyId).toList()
        );

        if (!existingAccess.getCompanyAccess().isEmpty()) {
            List<String> duplicatedCompanies = existingAccess.getCompanyAccess().stream()
                    .map(company -> company.getCompanyId() + ":" + company.getCompanyName()) // ✅ id와 name 같이 보여주기
                    .toList();

            throw new AlreadyRegisteredCompanyException("이미 등록된 회사가 존재합니다: " + String.join(", ", duplicatedCompanies));
        }

        UserCompanyAccess userCompanyAccess = UserCompanyAccess.of(command.getUserName(), command.getUserEmail(), companies);
        registerUserCompanyAccessPort.save(userCompanyAccess);

        return true;
    }
}
