package com.example.block22SpringAdvanced.infrastructure.abstract_services;

public interface CrudService<RQ, RS, ID> { // RQ -> Request | RS -> Response

    RS create(RQ request);
    RS read(ID id);
    RS update(RQ request, ID id);
    void delete(ID id);
}
