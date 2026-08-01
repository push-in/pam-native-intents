package dev.pam.intents
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import dev.pam.nativeapp.modules.*
import dev.pam.nativeapp.protocol.*
import org.json.JSONArray
class IntentsModule(private val context:Context):NativeModule{override fun invoke(method:String,payload:ByteArray,completion:ModuleCompletion){if(method!="register"){completion.complete(ModuleResultStatus.FAILURE,"Unknown method: $method".toByteArray());return};runCatching{val json=(WireMap.decode(payload)["json"]as?WireValue.Text)?.value?:"[]";val rows=JSONArray(json);val shortcuts=(0 until rows.length()).map{i->val row=rows.getJSONObject(i);ShortcutInfo.Builder(context,row.getString("id")).setShortLabel(row.getString("title")).setLongLabel(row.optString("subtitle").ifEmpty{row.getString("title")}).setRank(i).setIcon(Icon.createWithResource(context,context.applicationInfo.icon)).setIntent(Intent(Intent.ACTION_VIEW,Uri.parse(row.getString("deepLink"))).setPackage(context.packageName)).build()};context.getSystemService(ShortcutManager::class.java).dynamicShortcuts=shortcuts}.onSuccess{completion.complete(ModuleResultStatus.SUCCESS,WireMap.encode(emptyMap()))}.onFailure{completion.complete(ModuleResultStatus.FAILURE,it.message.orEmpty().take(1024).toByteArray())}}}
