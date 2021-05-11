package com.eaglesoar.payment.application.payment.charge;

import com.eaglesoar.payment.domain.model.Charge;
import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.service.ChargeCalculator;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ChargeCalculatorImpl implements ChargeCalculator {

    private final InternalKnowledgeBase chargesBase;

    public ChargeCalculatorImpl() {
        chargesBase = build(ResourceFactory.newClassPathResource("rules/charges.drl"));
    }

    @Override
    public List<Charge> calculate(Payment payment) {
        List<Charge> charges = new ArrayList<>();
        StatelessKieSession statelessKieSession = chargesBase.newStatelessKieSession();
        statelessKieSession.setGlobal("charges", charges);
        statelessKieSession.execute(payment);
        return charges;
    }


    private InternalKnowledgeBase build(Resource ruleResource) {
        final KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ruleResource, ResourceType.DRL);
        if (kBuilder.hasErrors()) {
            throw InvalidRuleFileException.of(kBuilder.getErrors());
        }
        Collection<KiePackage> knowledgePackages = kBuilder.getKnowledgePackages();
        InternalKnowledgeBase internalKnowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        internalKnowledgeBase.addPackages(knowledgePackages);
        return internalKnowledgeBase;
    }
}
