// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.config

import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import kotlinx.coroutines.flow.Flow
import sdl.moe.yabapi.data.live.CertificatePacketResponse
import sdl.moe.yabapi.data.live.commands.LiveCommand
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private typealias Config = LiveDanmakuSocketConfig
private typealias Wss = DefaultClientWebSocketSession

public class LiveDanmakuSocketConfig {
    internal var onHeartbeatResponse: suspend Wss.(popular: Flow<UInt>) -> Unit = {}

    internal var onCertificateResponse: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit = {}

    internal var onCommandResponse: suspend Wss.(command: Flow<LiveCommand>) -> Unit = {}
}

@OptIn(ExperimentalContracts::class)
public fun Config.onHeartbeatResponse(block: suspend Wss.(popular: Flow<UInt>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onHeartbeatResponse = block
}

@OptIn(ExperimentalContracts::class)
public fun Config.onCertificateResponse(block: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onCertificateResponse = block
}

@OptIn(ExperimentalContracts::class)
public fun Config.onCommandResponse(block: suspend Wss.(command: Flow<LiveCommand>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onCommandResponse = block
}
