// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import kotlin.test.Test
import kotlin.test.assertEquals

internal class RSAProviderTest {

    @Test
    fun test() {
        val rsa = RSAProvider
        val publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMKAPu0RTxVX+KJgdKbHJ3zY6dwEhuVmHXDtqCQI0MMQtIgpLY6M91qk/jBN8ldAAasPCn31g+hV2NxFzLqbnfcCAwEAAQ=="
        val privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAwoA+7RFPFVf4omB0pscnfNjp3ASG5WYdcO2oJAjQwxC0iCktjoz3WqT+ME3yV0ABqw8KffWD6FXY3EXMupud9wIDAQABAkEAu0GtU6BueyYPAoXKySbEJbbQsDrLhOAjSC4Gy2qRUrp8yT4wiu+zjb0wpR8aYOEjjbVLhNCUN2v/shOM0w+pgQIhAOSoVamCEnUOBrMabalMH0uJdZLn2sIrFvuY2IcmM7/ZAiEA2cJPNTPZhWSqw4/bKK60SDtFTB2qvQhi1PY6OBUmek8CIF9wC2EW01FcK1uyGjdheGZ010gb0ejdR1h4MVgnj83xAiEAk4mORvqpTzKkbDmkgEzWAvZ9fhO/obHz3Fwzl9nkpwsCIQDdisjg6fwB8RG1zL1VGO9u6eaAg25Wwbp5IjXn/q0byw=="
        val encrypted = rsa.encryptWithPublicKey(publicKey, "Hello World!")
        val decrypted = rsa.decryptWithPrivateKey(privateKey, encrypted)
        assertEquals("Hello World!", decrypted)
    }
}
