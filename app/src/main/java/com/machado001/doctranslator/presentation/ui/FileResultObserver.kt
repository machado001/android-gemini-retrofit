import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.machado001.doctranslator.TAG
import com.machado001.doctranslator.domain.DocumentTranslator
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class FileResultObserver(
    private val translator: DocumentTranslator,
    private val registry: ActivityResultRegistry,
    private val context: Context
) : DefaultLifecycleObserver {

    private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
            CoroutineScope(Job()).launch {
                val content = async { readPdfContent(uri ?: Uri.EMPTY) }
                translator.translate(content.await())
            }
        }
    }

    fun openFile() {
        getContent.launch("application/pdf")
    }

    private suspend fun readPdfContent(uri: Uri): String =
        withContext(Dispatchers.Default) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    PDDocument.load(inputStream).use { pdDocument ->
                        val textStripper = PDFTextStripper()
                        val extractedText = textStripper.getText(pdDocument)
                        Log.d(TAG, "Extracted PDF Content: $extractedText")
                        extractedText
                    }
                } ?: throw IOException("Failed to open InputStream for URI: $uri")
            } catch (e: Exception) {
                Log.e(TAG, "Error reading PDF content: ${e.message}")
                e.printStackTrace()
                "error"
            }
        }
}
