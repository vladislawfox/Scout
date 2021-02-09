package com.vladislawfox.scout.base.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import androidx.core.net.toUri
import com.vladislawfox.scout.base.extentions.getAttributeData


object UrlUtils {

    const val CHROME_PACKAGE = "com.android.chrome"

    /**
     * Open url in available browser that support Custom Tab, or open in the default browser
     *
     * @param context The source Context.
     * @param url The URL to load in the Custom Tab.
     */
    fun openUrl(context: Context, url: String) {
        val packageName = getAvailableCustomTabPackage(context)
        if (packageName != null) {
            openUrlInCustomTab(context, url, packageName)
        } else {
            openUrlInBrowser(context, url)
        }
    }


    /**
     * Open url in a stable version of Google Chrome's Custom Tab, or open in the default browser
     *
     * @param context The source Context.
     * @param url The URL to load in the Custom Tab.
     */
    fun openUrlInChromeTab(context: Context, url: String) {
        if (getCustomTabsPackages(context).contains(CHROME_PACKAGE)) {
            openUrlInCustomTab(context, url, CHROME_PACKAGE)
        } else {
            openUrlInBrowser(context, url)
        }
    }

    /**
     * Open url in the default browser
     *
     * @param context The source Context.
     * @param url The URL to load in the default browser.
     */
    fun openUrlInBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        try {
            context.startActivity(intent)
        } catch (ex: Exception) {
            GlobalExceptionLogger.logException(ex)
        }
    }

    private fun openUrlInCustomTab(
        context: Context,
        url: String,
        packageName: String
    ) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .apply {
                context.getAttributeData(android.R.attr.colorPrimary)?.let { color ->
                    setToolbarColor(color)
                }
            }
            .setShowTitle(true)
            .build()

        customTabsIntent.apply {
            intent.setPackage(packageName)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        customTabsIntent.launchUrl(context, Uri.parse(url))
    }


    /**
     * Get first available package that support Custom Tabs
     *
     * @param context android application context
     * @return application package name that support Custom Tabs. Could be null if there no apps that support Custom Tabs
     */
    fun getAvailableCustomTabPackage(context: Context): String? {
        return getCustomTabsPackages(context).firstOrNull()
    }

    /**
     * Get a list of packages that support Custom Tabs.
     *
     * @param context android application context
     * @return list of installed application package names that support Custom Tabs
     */
    fun getCustomTabsPackages(context: Context): List<String> {
        val packageManager = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.fromParts("http", "", null))

        // Get all apps that can handle VIEW intents.
        return packageManager.queryIntentActivities(activityIntent, 0)
            .map { info -> info.activityInfo.packageName }
            .filter { packageName ->
                val serviceIntent = Intent().apply {
                    action = CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
                    setPackage(packageName)
                }
                // Check if this package also resolves the Custom Tabs service.
                packageManager.resolveService(serviceIntent, 0) != null
            }
    }
}