package net.ap.lotm.data;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface ILotmData extends INBTSerializable<CompoundTag> {

    void tick();

    void gen();


}
