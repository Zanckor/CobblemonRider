package dev.zanckor.cobblemonridingfabric.network.handler;

import dev.zanckor.cobblemonridingfabric.network.handler.client.ConfigReceiver;

public class ClientReceiveHandler {
    public static void register() {
        ConfigReceiver.receive();
    }
}
