package moe.sdl.yabapi.exception

import kotlinx.serialization.encoding.Decoder

public class UnsupportedDecoderException(decoder: Decoder) : IllegalArgumentException("Unsupported decoder $decoder")
