package ar.com.scacchipa.eldarwallet.data.sourcedata

import ar.com.scacchipa.eldarwallet.util.Constants.Companion.API_KEY
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class QrCodeService @Inject constructor() {

    val client = OkHttpClient()

    val mediaType =
        "multipart/form-data; boundary=---011000010111000001101001".toMediaTypeOrNull()

    fun fetchQrCodeImageBytes(text: String, fill: String = "black", back: String = "white") : Response {

        val body = RequestBody.create(
            mediaType,
            "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"text\"\r\n\r\n$text\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"fill\"\r\n\r\n$fill\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"back\"\r\n\r\n$back\r\n-----011000010111000001101001--\r\n\r\n"
        )

        val request: Request = Request.Builder()
            .url("https://qrcode68.p.rapidapi.com/classic")
            .post(body)
            .addHeader("x-rapidapi-key", API_KEY)
            .addHeader("x-rapidapi-host", "qrcode68.p.rapidapi.com")
            .addHeader(
                "Content-Type",
                "multipart/form-data; boundary=---011000010111000001101001"
            )
            .build()

        return client.newCall(request).execute()
    }
}
