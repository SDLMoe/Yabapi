// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.config

import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import kotlinx.coroutines.flow.Flow
import sdl.moe.yabapi.data.live.CertificatePacketResponse
import sdl.moe.yabapi.data.live.commands.LiveCommand

private typealias Config = LiveDanmakuSocketConfig
private typealias Wss = DefaultClientWebSocketSession

public class LiveDanmakuSocketConfig {
    public var onHeartbeatResponse: suspend Wss.(popular: Flow<UInt>) -> Unit = {}

    public var onCertificateResponse: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit = {}

    public var onCommandResponse: suspend Wss.(command: Flow<LiveCommand>) -> Unit = {}
}

public fun Config.onHeartbeatResponse(block: suspend Wss.(popular: Flow<UInt>) -> Unit) {
    onHeartbeatResponse = block
}

public fun Config.onCertificateResponse(block: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit) {
    onCertificateResponse = block
}

public fun Config.onCommandResponse(block: suspend Wss.(command: Flow<LiveCommand>) -> Unit) {
    onCommandResponse = block
}
