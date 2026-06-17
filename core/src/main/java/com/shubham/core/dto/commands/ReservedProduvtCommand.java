package com.shubham.core.dto.commands;

import java.util.UUID;

public record ReservedProduvtCommand(
        UUID prodctId,
        Integer productQuantity,
        UUID orderId)
{ }
