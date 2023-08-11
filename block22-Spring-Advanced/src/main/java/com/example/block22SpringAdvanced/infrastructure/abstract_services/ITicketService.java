package com.example.block22SpringAdvanced.infrastructure.abstract_services;

import com.example.block22SpringAdvanced.application.models.request.TicketRequest;
import com.example.block22SpringAdvanced.application.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID> {

    BigDecimal findPrice(Long flyId, Currency currency);
}
