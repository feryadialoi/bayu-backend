package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Pin;
import dev.feryadi.backend.bayu.model.response.PinResponse;

public interface PinMapper {
    PinResponse mapPinToPinResponse(Pin pin);
}
