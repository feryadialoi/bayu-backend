package dev.feryadi.backend.bayu.commandimpl.pin;

import dev.feryadi.backend.bayu.command.pin.UpdateUserPinCommand;
import dev.feryadi.backend.bayu.entity.Pin;
import dev.feryadi.backend.bayu.exception.PinNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.UpdatePinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;
import dev.feryadi.backend.bayu.modelmapper.PinMapper;
import dev.feryadi.backend.bayu.repository.PinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UpdateUserPinCommandImpl implements UpdateUserPinCommand {

    private final PinRepository pinRepository;
    private final PinMapper pinMapper;

    @Override
    public PinResponse execute(UpdatePinCommandRequest request) {
        Optional<Pin> optionalPin = pinRepository.findById(request.getPinId());

        if (optionalPin.isPresent()) {
            Pin pin = optionalPin.get();

            pin.setPin(request.getUpdateUserPinRequest().getPin());

            pin = pinRepository.save(pin);

            return pinMapper.mapPinToPinResponse(pin);
        }

        throw new PinNotFoundException("pin with id " + request.getPinId() + " not found");
    }
}
