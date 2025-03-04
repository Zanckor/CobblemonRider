package dev.zanckor.cobblemonridingfabric.network.handler;

import dev.zanckor.cobblemonridingfabric.network.handler.server.KeyReceiver;
import dev.zanckor.cobblemonridingfabric.network.handler.server.MountReceiver;

public class ServerReceiveHandler {
    public static void register() {
        KeyReceiver.receive();
        MountReceiver.receive();
    }
}
