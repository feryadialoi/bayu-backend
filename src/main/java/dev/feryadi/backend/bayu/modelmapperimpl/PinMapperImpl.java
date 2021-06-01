package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Pin;
import dev.feryadi.backend.bayu.model.response.PinResponse;
import dev.feryadi.backend.bayu.modelmapper.PinMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PinMapperImpl implements PinMapper {

    @Override
    public PinResponse mapPinToPinResponse(Pin pin) {
        return PinResponse.builder()
                .id(pin.getId())
                .pin(pin.getPin())
                .build();
    }
}
