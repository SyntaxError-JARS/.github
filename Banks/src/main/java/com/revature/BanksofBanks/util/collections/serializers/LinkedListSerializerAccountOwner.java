package com.revature.BanksofBanks.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.BanksofBanks.models.AccountOwner;
import com.revature.BanksofBanks.util.collections.LinkedList;

import java.io.IOException;

public class LinkedListSerializerAccountOwner extends StdSerializer<LinkedList> {

    public LinkedListSerializerAccountOwner() {
        this(null);
    }

    public LinkedListSerializerAccountOwner(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            AccountOwner accountowner = (AccountOwner) linkedList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("fname", trainer.getFname());
            jsonGenerator.writeStringField("lname", trainer.getLname());
            jsonGenerator.writeStringField("email", trainer.getEmail());
            jsonGenerator.writeStringField("password", trainer.getPassword());
            jsonGenerator.writeStringField("dob", trainer.getDob());
            jsonGenerator.writeEndObject();
        }

    }
}
