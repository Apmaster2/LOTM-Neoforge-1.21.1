package net.ap.lotm.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;

public class LotmDataSerializer implements IAttachmentSerializer<CompoundTag, ILotmData> {

    @Override
    public ILotmData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
        ILotmData data = new LotmData((LivingEntity) holder);
        data.deserializeNBT(provider, tag);
        return data;
    }

    @Override
    public @Nullable CompoundTag write(ILotmData iLotmData, HolderLookup.Provider provider) {
        return iLotmData.serializeNBT(provider);
    }

}
