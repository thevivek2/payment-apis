package com.eaglesoar.payment.application.payment.charge;

import com.eaglesoar.payment.domain.exception.BaseException;
import org.kie.internal.builder.KnowledgeBuilderErrors;

public class InvalidRuleFileException extends BaseException {

    public InvalidRuleFileException(String message) {
        super(message);
    }

    public static InvalidRuleFileException of(KnowledgeBuilderErrors errors) {
        return new InvalidRuleFileException(errors.toString());
    }
}
