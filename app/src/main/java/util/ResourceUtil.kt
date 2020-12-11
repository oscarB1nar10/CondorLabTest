package util

import java.io.*

object ResourceUtil {

    private const val API_RESPONSE_DIR = "api_response/"
    private const val IMAGES_DIR = "images/"

    @Throws(IOException::class)
    fun convertStreamToString(inputStream: InputStream?): String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var s: String? = br.readLine()
        while (s != null) {
            sb.append(s)
            s = br.readLine()
        }
        return sb.toString()
    }

    /**
     * Reads a resource file to String.
     */
    @Throws(IOException::class)
    fun readResponseFromFile(classLoader: ClassLoader?, filename: String): String {

        if (classLoader == null) return ""

        val inputStream =
            classLoader.getResourceAsStream(API_RESPONSE_DIR + filename)
        return convertStreamToString(inputStream)
    }

    @Throws(IOException::class)
    fun getImageFileAsStream(classLoader: ClassLoader?, filename: String): InputStream? {
        return classLoader?.getResourceAsStream(IMAGES_DIR + filename)
    }
}