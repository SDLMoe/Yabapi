// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.exception

import kotlinx.serialization.encoding.Decoder

public class UnsupportedDecoderException(decoder: Decoder): IllegalArgumentException("Unsupported decoder $decoder")
