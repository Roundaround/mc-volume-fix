package me.roundaround.volumefix.gametest;

import me.roundaround.allay.api.gametest.ClientGameTest;
import me.roundaround.trove.gametest.ClientTest;
import me.roundaround.trove.gametest.ClientTestContext;
import me.roundaround.trove.gametest.ClientWorld;
import me.roundaround.trove.gametest.GameTestAssertionException;

/**
 * Boots a world and plays a sound so the SoundEngine / AbstractSoundInstance mixins run
 * on a real sound. The mod has no screen or HUD, so the value is proving those mixins
 * applied and a sound plays without crashing the client.
 */
@ClientGameTest
public class VolumeFixSoundSmokeTest implements ClientTest {
  @Override
  public void runTest(ClientTestContext context) {
    try (ClientWorld world = context.worldBuilder().creative().stopTime(true).create()) {
      world.teleport(0.5, 65.0, 0.5);
      world.runCommand("playsound minecraft:entity.experience_orb.pickup master @s");
      context.waitTicks(10);

      if (context.minecraft() == null) {
        throw new GameTestAssertionException("client survived playing a sound through the volume-fix mixins");
      }
    }
  }
}
