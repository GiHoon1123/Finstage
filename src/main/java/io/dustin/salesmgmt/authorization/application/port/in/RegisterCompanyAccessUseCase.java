package io.dustin.salesmgmt.authorization.application.port.in;

import io.dustin.salesmgmt.authorization.application.port.in.command.RegisterCompanyAccessCommand;
public interface RegisterCompanyAccessUseCase {
    boolean register(RegisterCompanyAccessCommand command);
}
