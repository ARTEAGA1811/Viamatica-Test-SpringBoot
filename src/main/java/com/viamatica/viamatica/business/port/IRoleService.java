package com.viamatica.viamatica.business.port;

import com.viamatica.viamatica.domain.dto.Role;

public interface IRoleService extends IEntityCrudService<Role, Long> {
    Role getRoleByName(String name);
}
