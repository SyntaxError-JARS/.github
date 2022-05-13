package com.revature.BanksofBanks.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.BanksofBanks.models.Transactions;
import com.revature.BanksofBanks.models.AccountOwner;
import com.revature.BanksofBanks.util.collections.LinkedList;

import java.io.IOException;

public class LinkedListSerializerTransactions extends StdSerializer<LinkedList> {

    public LinkedListSerializerTransactions() {
        this(null);
    }

    public LinkedListSerializerTransactions(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            Transactions transaction = (Transactions) linkedList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("abilityName", transaction.getAbilityName());
            jsonGenerator.writeNumberField("atkMultiplier", transaction.getAtkMultiplier());
            jsonGenerator.writeNumberField("dmgType", transaction.getDmgType());
            jsonGenerator.writeEndObject();
        }

    }
}