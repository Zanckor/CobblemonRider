package dev.zanckor.cobblemonridingfabric.network.handler;

import dev.zanckor.cobblemonridingfabric.network.handler.server.KeyReceiver;

public class ServerReceiveHandler {
    public static void register() {
        KeyReceiver.receive();
    }
}
