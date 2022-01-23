package xyz.minejs.sample

import com.eclipsesource.v8.JavaCallback
import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.netherald.minejs.bukkit.native.NativeAddon
import org.netherald.minejs.bukkit.utils.ObjectUtils
import org.netherald.minejs.common.ScriptLoader
import java.util.*

class SampleAddon : NativeAddon() {
    override fun init() {
        registerFunction("testing") { _, parameters ->
            val v8Player = parameters[0] as V8Object

            Bukkit.getPlayer(
                UUID.fromString(v8Player.getString("uuid"))
            )?.inventory?.addItem(
                ObjectUtils.fromV8ItemStack(parameters[1] as V8Object, parameters.runtime)
            )
        }

        registerFunction("return", JavaCallback { _, _ ->
            return@JavaCallback "I returned string!" // You can use String, Double, Int, V8Object, V8Array for return value
        })

        registerListener(object : Listener {
            @EventHandler
            fun onInteract(event: PlayerInteractEvent) {
                if(event.action != Action.RIGHT_CLICK_BLOCK) return

                if(event.player.inventory.itemInMainHand.type == Material.DIAMOND) {
                    ScriptLoader.invokeEvent("onDiamond") {
                        // Create array
                        val array = V8Array(runtime)
                        array.push(1234)

                        // Add values into the "event" param object
                        add("int", 1234)
                        add("double", 1234.4)
                        add("string", "just string")
                        add("array", array)
                    }
                }
            }
        })

    }
}