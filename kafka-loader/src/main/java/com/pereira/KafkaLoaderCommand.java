package com.pereira;

import com.pereira.domain.Message;
import com.pereira.producer.MessageProducer;
import io.micronaut.configuration.picocli.PicocliRunner;
import lombok.SneakyThrows;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.time.Instant;

@Command(name = "kafka-loader", description = "...",
        mixinStandardHelpOptions = true)
public class KafkaLoaderCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Option(names = {"-m", "--messages"}, description = "Number of messages in between")
    int messages = 1000;

    @Inject
    private MessageProducer messageProducer;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(KafkaLoaderCommand.class, args);
    }

    @SneakyThrows
    public void run() {

        Message fast =
                new Message(1, "+12063333333", "+12064444444", "Hello, world!", Instant.now());

        Message slow =
                new Message(2, "+12063333333", "+12064444444", "Hello, world!", Instant.now());

        messageProducer.sendMessage(fast);

        Thread.sleep(3000);

        for (int i = 0; i < messages; i++) {
            messageProducer.sendMessage(slow);
        }

        Thread.sleep(3000);

        messageProducer.sendMessage(fast);
    }
}
