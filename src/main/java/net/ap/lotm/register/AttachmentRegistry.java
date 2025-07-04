package net.ap.lotm.register;

import net.ap.lotm.Lotm;
import net.ap.lotm.data.ILotmData;
import net.ap.lotm.data.LotmData;
import net.ap.lotm.data.LotmDataSerializer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentRegistry {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES,
            Lotm.MOD_ID
    );

    public static final Supplier<AttachmentType<ILotmData>> LOTM_DATA_ATTACHMENT = ATTACHMENTS.register(
            "lotm_data_attachment",
            AttachmentType.<ILotmData>builder(holder -> new LotmData((LivingEntity) holder)).serialize(new LotmDataSerializer()).copyOnDeath()::build
    );


    public static void register(IEventBus eventBus) {
        ATTACHMENTS.register(eventBus);
    }

}
