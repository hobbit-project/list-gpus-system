package org.hobbit.sdk.examples.examplebenchmark.system;

import java.io.InputStream;
import java.net.Socket;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.vocabulary.RDF;
import org.hobbit.core.components.AbstractSystemAdapter;
import org.hobbit.core.rabbit.RabbitMQUtils;
import org.hobbit.sdk.examples.examplebenchmark.Constants;
import org.hobbit.vocab.HOBBIT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hobbit.core.Constants.CONTAINER_TYPE_SYSTEM;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;


public class SystemAdapter extends AbstractSystemAdapter implements Supplier<String> {
    private static final Logger logger = LoggerFactory.getLogger(SystemAdapter.class);
    private Map<String, String> parameters = new HashMap<>();

    protected String container;

    @Override
    public void init() throws Exception {
        super.init();

        container = createContainer("git.project-hobbit.eu:4567/gitadmin/list-gpus-system/nvidia-smi-wrapper", CONTAINER_TYPE_SYSTEM, null);
    }

    @Override
    public void receiveGeneratedData(byte[] data) {
    }

    @Override
    public void receiveGeneratedTask(String taskId, byte[] data) {
    }

    public void receiveCommand(byte command, byte[] data) {
        if (command == Constants.INPUT_DATA_COMMAND) {
            String input = RabbitMQUtils.readString(data);
            logger.info("Input (discarded): {}", input);
            String output = get();
            if (output != null) {
                logger.info("Output: {}", output);
                try {
                    sendToCmdQueue(Constants.OUTPUT_DATA_COMMAND, RabbitMQUtils.writeString(output));
                    terminate(null);
                } catch (IOException e) {
                    terminate(e);
                }
            }
        } else {
            super.receiveCommand(command, data);
        }
    }

    @Override
    public String get() {
        try {
            StringBuilder builder = new StringBuilder();
            Socket socket = new Socket(container, 5555);
            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int read;
            while((read = input.read(buffer)) != -1) {
                String output = new String(buffer, 0, read);
                builder.append(output);
            };
            socket.close();
            return builder.toString();
        } catch (IOException e) {
            terminate(e);
            return null;
        }
    }

}

