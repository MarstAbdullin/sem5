package ru.javalab.repositories;

import ru.javalab.dto.TroupeDto;
import ru.javalab.dto.TroupeRequest;

import java.util.List;

public interface TroupesByRequestRepository {
    List<TroupeDto> findByRequest(TroupeRequest troupeRequest);
}
