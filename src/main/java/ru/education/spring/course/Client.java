package ru.education.spring.course;

import com.example.grpc.Service;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.MessageServiceGrpc;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext().build();

        //создание stub
        MessageServiceGrpc.MessageServiceBlockingStub stub = MessageServiceGrpc.newBlockingStub(channel);

        Service.Request request = Service.Request.newBuilder().setObject("obj").build();

        //Вызов удаленной процедуры с сервера
        Service.Response response = stub.response(request);

        System.out.println(response);

        channel.shutdownNow();

    }
}
