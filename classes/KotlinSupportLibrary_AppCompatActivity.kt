package br.com.edsilfer.kotlin_support.extensions

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import br.com.edsilfer.kotlin_support.R
import br.com.edsilfer.kotlin_support.commons.Constants
import br.com.edsilfer.kotlin_support.model.DirectoryPath
import br.com.edsilfer.kotlin_support.model.Email
import br.com.edsilfer.kotlin_support.model.Sender
import br.com.edsilfer.kotlin_support.presenter.dialog.DialogInput
import br.com.edsilfer.kotlin_support.service.communication.EmailManager
import br.com.edsilfer.kotlin_support.service.files.SharedPreferencesUtil
import com.afollestad.materialdialogs.MaterialDialog
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


enum class DisplayContainer {DIALOG, SNACKBAR }

enum class LogLevel { WARNING, ERROR }

// UI/NOTIFICATIONS ================================================================================
fun AppCompatActivity.showInputDialog(title: String,
                                      hint: String,
                                      onAccept: (text: String) -> Unit = {},
                                      onCancel: (text: String) -> Unit = {},
                                      onDismiss: (text: String) -> Unit = {},
                                      inputText: String? = "",
                                      inputTextSize: Float = 16f) {
    DialogInput(this)
            .setDialogTitle(title)
            .setInputHint(hint)
            .onAccept(onAccept)
            .onCancel(onCancel)
            .onDismiss(onDismiss)
            .setInputText(inputText!!)
            .setInputTextSize(inputTextSize)
            .show()
}

fun AppCompatActivity.showUnderConstructionPopUp() {
    val value = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
    MaterialDialog.Builder(this)
            .title(getString(R.string.str_dlg_under_construction_title))
            .titleColor(value.data)
            .content(getString(R.string.str_dlg_under_construction_content))
            .positiveText(getString(R.string.str_commons_button_okay))
            .positiveColor(value.data)
            .negativeText("")
            .show()
}

// STRING PARAMETERS  ==============================================================================
fun AppCompatActivity.showErrorPopUp(content: String) {
    runOnUiThread {
        MaterialDialog.Builder(this)
                .title(Html.fromHtml("<b><font color='#c53929'> ${getString(R.string.str_dlg_error_title)}</d></font>"))
                .content(content)
                .positiveText(getString(R.string.str_commons_button_okay))
                .positiveColor(resources.getColor(android.R.color.holo_red_dark))
                .show()
    }
}

fun AppCompatActivity.showWarningPopUp(content: String) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#FFCC00'>${getString(R.string.str_dlg_warning_title)}</d></font>"))
            .content(content)
            .positiveText(getString(R.string.str_commons_button_okay))
            .positiveColor(resources.getColor(R.color.rsc_dialog_warning))
            .show()
}

fun AppCompatActivity.showPopUp(
        title: String,
        content: String,
        negativeText: String = getString(R.string.str_commons_button_cancel),
        onPositive: () -> Unit = {}) {
    runOnUiThread {
        val value = TypedValue()
        theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
        MaterialDialog.Builder(this)
                .title(title)
                .titleColor(value.data)
                .content(content)
                .positiveText(getString(R.string.str_commons_button_okay))
                .positiveColor(value.data)
                .negativeColor(value.data)
                .negativeText(negativeText)
                .onPositive { materialDialog, dialogAction -> onPositive() }
                .show()
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
fun AppCompatActivity.paintStatusBar(color: Int) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = resources.getColor(color)
}

fun AppCompatActivity.showSoftKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

// RESOURCE PARAMETERS =============================================================================
fun AppCompatActivity.showWarningPopUp(content: Int) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#FFCC00'>${getString(R.string.str_dlg_warning_title)}</d></font>"))
            .content(getString(content))
            .positiveText(getString(R.string.str_commons_button_okay))
            .positiveColor(resources.getColor(R.color.rsc_dialog_warning))
            .show()
}

fun AppCompatActivity.showErrorPopUp(content: Int) {
    MaterialDialog.Builder(this)
            .title(Html.fromHtml("<b><font color='#c53929'> ${getString(R.string.str_dlg_error_title)}</d></font>"))
            .content(getString(content))
            .positiveText(getString(R.string.str_commons_button_okay))
            .positiveColor(resources.getColor(android.R.color.holo_red_dark))
            .show()
}

fun AppCompatActivity.showPopUp(title: Int, content: Int) {
    val value = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
    MaterialDialog.Builder(this)
            .title(getString(title))
            .titleColor(value.data)
            .content(getString(content))
            .positiveText(getString(R.string.str_commons_button_okay))
            .positiveColor(value.data)
            .negativeColor(value.data)
            .negativeText(getString(R.string.str_commons_button_cancel))
            .show()
}

// LOADING =========================================================================================
fun AppCompatActivity.showCircularProgressBar() {
    try {
        runOnUiThread {
            findViewById(R.id.circularProgressBar)!!
                    .animate()
                    .translationY(getResources().getDimension(R.dimen.rsc_circular_progress_bar_height) / 2)
                    .alpha(1f).duration = Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR_CIRCULAR_DURATION.toLong()
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }

}

fun AppCompatActivity.hideCircularProgressBar() {
    try {
        runOnUiThread {
            findViewById(R.id.circularProgressBar)!!
                    .animate().translationY(getResources().getDimension(R.dimen.rsc_circular_progress_bar_height) / 2)
                    .alpha(0f)
                    .duration = Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR_CIRCULAR_DURATION.toLong()
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }
}

fun AppCompatActivity.showIndeterminateProgressBar(color: Int = -1) {
    try {
        runOnUiThread {
            val progressBar = findViewById(R.id.progress_bar) as ProgressBar
            progressBar.visibility = ProgressBar.VISIBLE
            if (color != -1) {
                progressBar.indeterminateDrawable.setColorFilter(
                        resources.getColor(color),
                        android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR + "extra: ${e.message}"))
    }
}

fun AppCompatActivity.hideIndeterminateProgressBar() {
    try {
        runOnUiThread {
            val progressBar = findViewById(R.id.progress_bar) as ProgressBar
            progressBar.visibility = ProgressBar.GONE
        }
    } catch (e: Exception) {
        throw RuntimeException(
                Constants.EXCEPTION_LAYOUT_MISSING.replace(Constants.PLACEHOLDER, Constants.LoadingInterface.LAYOUT_PROGRESS_BAR_INDETERMINATE_CIRCULAR))
    }
}

// SCREEN SHOT =====================================================================================
fun AppCompatActivity.takeScreenShot(
        location: DirectoryPath = DirectoryPath.INTERNAL,
        path: String = "",
        fName: String = "",
        showToast: Boolean = true,
        openScreenShot: Boolean = true
): String {
    if (location == DirectoryPath.EXTERNAL && !isExternalStorageWritable()) throw IllegalStateException("External storage is not writable")
    val file = createFile(location, fName, path)
    writeFile(file)
    if (openScreenShot) openScreenShot(location, file.absolutePath)
    log("Saving screenshot on: ${file.absolutePath}")
    if (showToast) toast("Saving screenshot on: ${file.absolutePath}")
    return file.absolutePath
}

private fun AppCompatActivity.writeFile(file: File) {
    val outputStream = FileOutputStream(file)
    val quality = 100
    window.decorView.rootView.getBitmap().compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
    outputStream.flush()
    outputStream.close()
}

private fun AppCompatActivity.createFile(
        location: DirectoryPath,
        fName: String = "",
        path: String = "",
        extension: String = "jpeg"
): File {
    val result = File(createScreenShotDir(location, path), getFileName(extension, fName))
    result.createNewFile()
    return result
}

private fun AppCompatActivity.createScreenShotDir(location: DirectoryPath, path: String): File {
    val absolutePath = StringBuilder(location.getValue(this))
    if (!path.isNullOrEmpty()) absolutePath.append("$path/")
    val dir = File(absolutePath.toString())
    dir.mkdirs()
    return dir
}

private fun getFileName(extension: String, fName: String): String {
    var fileName = ""
    if (fName.isNullOrBlank()) {
        fileName = "${SimpleDateFormat("yyyy-MM-dd_hh:mm:ss").format(Date())}.$extension"
    } else {
        fileName = "$fName.$extension"
    }
    return fileName
}

fun AppCompatActivity.openScreenShot(location: DirectoryPath, path: String) {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    when (location) {
        DirectoryPath.EXTERNAL -> intent.setDataAndType(Uri.fromFile(File(path)), "image/*");
        DirectoryPath.INTERNAL -> intent.setDataAndType(Uri.parse("content://${applicationContext.packageName}/$path"), "image/*")

    }
    startActivity(intent)
}

// PERMISSIONS =====================================================================================
fun AppCompatActivity.requestPermission(permission: String): Boolean {
    if (Build.VERSION.SDK_INT >= 23) {
        if (checkPermission(permission)) {
            log("$permission is granted")
            return true
        } else {
            log("$permission is denied")
            ActivityCompat.requestPermissions(this, arrayOf<String>(permission), 1)
            return false
        }
    } else {
        log("API below 23, $permission is granted")
        return true
    }
}

fun AppCompatActivity.checkPermission(permission: String): Boolean {
    return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}

fun AppCompatActivity.isExternalStorageWritable(): Boolean {
    val state = Environment.getExternalStorageState()
    if (Environment.MEDIA_MOUNTED == state && checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        Log.i("DirectoryPath", "External is writable")
        return true
    }
    Log.i("DirectoryPath", "External is not writable")
    return false
}

// COMMUNICATION ===================================================================================
fun AppCompatActivity.sendEmail(sender: Sender, email: Email) {
    if (!checkPermission(android.Manifest.permission.INTERNET)) throw IllegalStateException("App must have INTERNET acess in order to send e-mails")
    val manager = EmailManager(sender)
    manager.sendMail(email)
}

fun AppCompatActivity.putProperty(key: String, value: Any) {
    SharedPreferencesUtil.putProperty(this, key, value)
}

fun AppCompatActivity.getProperty(key: String, defaultValue: Any): Any? {
    return SharedPreferencesUtil.getProperty(this, key, defaultValue)
}

// OTHER ===========================================================================================