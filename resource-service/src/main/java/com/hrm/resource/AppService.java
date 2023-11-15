package com.hrm.resource;

import java.util.List;

public interface AppService {

    EntityObject get(Long id);

    List<EntityObject> getAll();

    EntityObject save(EntityObject entity);

    void delete(Long id);
}
