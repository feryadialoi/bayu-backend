package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.TransferResponse;

public interface TransferMapper {
    TransferResponse mapTransferToTransferResponse(Mutation mutation);
}
