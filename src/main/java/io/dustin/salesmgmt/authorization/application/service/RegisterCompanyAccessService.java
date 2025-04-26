package io.dustin.salesmgmt.authorization.application.service;

import io.dustin.salesmgmt.authorization.application.port.in.RegisterCompanyAccessUseCase;
import io.dustin.salesmgmt.authorization.application.port.in.command.RegisterCompanyAccessCommand;
import io.dustin.salesmgmt.authorization.application.port.out.RegisterUserCompanyAccessPort;
import io.dustin.salesmgmt.authorization.domain.Company;
import io.dustin.salesmgmt.authorization.domain.UserCompanyAccess;
import io.dustin.salesmgmt.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class RegisterCompanyAccessService implements RegisterCompanyAccessUseCase {

    private final RegisterUserCompanyAccessPort registerUserCompanyAccessPort;

    @Override
    public boolean register(RegisterCompanyAccessCommand command) {

        List<Company> companies = command.getCompanies().stream()
                .map(c -> Company.of(c.getCompanyId(), c.getCompanyName()))
                .toList();;

        UserCompanyAccess userCompanyAccess = UserCompanyAccess.of(command.getUserName(), command.getUserEmail(), companies);

        registerUserCompanyAccessPort.save(userCompanyAccess);


        return true;
    }
}
