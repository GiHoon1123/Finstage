package io.dustin.finstage.authorization.application.port.in;

import io.dustin.finstage.authorization.application.port.in.command.RegisterCompanyAccessCommand;
public interface RegisterCompanyAccessUseCase {
    boolean register(RegisterCompanyAccessCommand command);
}
