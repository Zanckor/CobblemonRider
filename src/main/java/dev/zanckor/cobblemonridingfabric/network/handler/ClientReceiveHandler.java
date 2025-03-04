package dev.zanckor.cobblemonridingfabric.network.handler;

import dev.zanckor.cobblemonridingfabric.network.handler.client.ConfigReceiver;
import dev.zanckor.cobblemonridingfabric.network.handler.server.MountReceiver;

public class ClientReceiveHandler {
    public static void register() {
        ConfigReceiver.receive();
    }
}
